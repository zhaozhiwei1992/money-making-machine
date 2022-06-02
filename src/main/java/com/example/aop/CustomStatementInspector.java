package com.example.aop;

import cn.hutool.extra.spring.SpringUtil;
import com.example.security.SecurityUtils;
import com.example.service.DataPermissionsService;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.Statements;
import net.sf.jsqlparser.statement.select.FromItem;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import org.hibernate.resource.jdbc.spi.StatementInspector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhaozhiwei
 * @version V1.0
 * @Title: DataRightInterceptor
 * @Package com/example/aop/DataRightInterceptor.java
 * @Description: 通过实现org.hibernate.resource.jdbc.spi.StatementInspector
 * 动态调整sql, 扩展特殊sql相关功能
 * <p>
 * 为什么使用sql方式? 后续采集表没有domain对象, 都是原生sql, 可兼容
 * <p>
 * 通过druid的filter可以实现同样的功能
 * @date 2022/5/14 下午10:22
 */
public class CustomStatementInspector implements StatementInspector {

    private static final Logger log = LoggerFactory.getLogger(CustomStatementInspector.class);

    /**
     * 重写StatementInspector的inspect接口，
     * 参数为hibernate处理后的原始SQL，返回值为我们修改后的SQL
     *
     * @param sql
     * @return
     */
    @Override
    public String inspect(String sql) {
        try {
            // 1. 是否登录状态, 未登录不允许查看数据
            final boolean authenticated = SecurityUtils.isAuthenticated();
            if (!authenticated) {
                // 可以显示未登录不允许查看任何数据 1<>1
                log.info("当前未登录, 看着办吧");
                return sql;
            }

            // 2. 登录状态下加载权限, 根据类型增加不同sql过滤
            log.info("原SQL：{}", sql);
            Statements statements = CCJSqlParserUtil.parseStatements(sql);
            StringBuilder sqlStringBuilder = new StringBuilder();
            int i = 0;
            for (Statement statement : statements.getStatements()) {
                if (null != statement) {
                    if (i++ > 0) {
                        sqlStringBuilder.append(';');
                    }
                    sqlStringBuilder.append(this.doSqlParser(statement));
                }
            }
            String newSql = sqlStringBuilder.toString();
            log.info("处理后SQL：{}", newSql);
            return newSql;
        } catch (Exception e) {
            log.error("组织筛选解析失败，解析SQL异常", e);
            e.printStackTrace();
        }
        return null;
    }

    private String doSqlParser(Statement statement) {
        if (statement instanceof Select) {
            this.doSelectParser(((Select) statement).getSelectBody());
        }
        return statement.toString();
    }

    public void doSelectParser(SelectBody selectBody) {
        if (selectBody instanceof PlainSelect) {
            processPlainSelect((PlainSelect) selectBody);
        }
    }

    /**
     * @param plainSelect :
     * @data: 2022/5/14-下午11:20
     * @User: zhaozhiwei
     * @method: processPlainSelect
     * @return: void
     * @Description: 在这里扩展各种的条件
     */
    protected void processPlainSelect(PlainSelect plainSelect) {
        FromItem fromItem = plainSelect.getFromItem();
        if (fromItem instanceof Table) {
            Table fromTable = (Table) fromItem;
            final DataPermissionsService dataPermissionsService = SpringUtil.getBean(DataPermissionsService.class);
            final Expression dataPermissionExpression = dataPermissionsService.buildDataPermissionCondition(fromTable);
            plainSelect.setWhere(this.joinExpression(plainSelect.getWhere(), dataPermissionExpression));
        }
    }

    /**
     * @param currentExpression :
     * @param appendExpression  :
     * @data: 2022/5/14-下午10:40
     * @User: zhaozhiwei
     * @method: builderExpression
     * @return: net.sf.jsqlparser.expression.Expression
     * @Description: 描述
     * 根据规则，　添加权限条件
     */
    protected Expression joinExpression(Expression currentExpression, Expression appendExpression) {
        final StringValue stringValue = new StringValue("1");
        final EqualsTo allCondition = new EqualsTo(stringValue, stringValue);

        if (currentExpression == null) {
            //            之前如果是全部查询(没有条件), 则返回1=1
            currentExpression = allCondition;
        }

        if (currentExpression instanceof OrExpression) {
            return new AndExpression(new Parenthesis(currentExpression), appendExpression);
        } else {
            return new AndExpression(currentExpression, appendExpression);
        }
    }
}
