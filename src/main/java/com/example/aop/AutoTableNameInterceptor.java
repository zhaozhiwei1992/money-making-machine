package com.example.aop;

import cn.hutool.core.util.StrUtil;
import java.util.Map;
import java.util.Objects;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.Statements;
import net.sf.jsqlparser.statement.select.FromItem;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import org.hibernate.EmptyInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhaozhiwei
 * @version V1.0
 * @Title: AutoTableNameInterceptor
 * @Package com/example/aop/AutoTableNameInterceptor.java
 * @Description: 一些特殊场景，如基础数据等, 需要不同的表,但是映射相同的实体类
 * 如基础要素表, 无论是总表ele_union还是分表ele_vd001001 实体都可以用同一个
 *
 *
Map<String, String> map = new HashMap<>();
map.put("oldName", name);
map.put("newName", toName);
AutoTableNameInterceptor.myTable.set(map);
 * <p>
 * 参考: https://www.cxyzjd.com/article/qq_36299945/108095741
 * @date 2022/6/17 下午2:20
 */
public class AutoTableNameInterceptor extends EmptyInterceptor {

    private static final Logger log = LoggerFactory.getLogger(AutoTableNameInterceptor.class);

    public static ThreadLocal<Map<String, String>> replaceTable = new ThreadLocal<>();

    @Override
    public String onPrepareStatement(String sql) {
        Map<String, String> map = replaceTable.get();

        //        必要时才会去替换
        if (Objects.isNull(map)) {
            return sql;
        }

        String oldName = map.get("oldName");
        String newName = map.get("newName");
        if (StrUtil.isEmpty(oldName) || StrUtil.isEmpty(newName)) {
            return sql;
        }

        try {
            Statements statements = CCJSqlParserUtil.parseStatements(sql);
            StringBuilder sqlStringBuilder = new StringBuilder();
            int i = 0;
            for (Statement statement : statements.getStatements()) {
                if (!Objects.isNull(statement)) {
                    if (i++ > 0) {
                        sqlStringBuilder.append(';');
                    }
                    if (statement instanceof Select) {
                        final SelectBody selectBody = ((Select) statement).getSelectBody();
                        if (selectBody instanceof PlainSelect) {
                            final FromItem fromItem = ((PlainSelect) selectBody).getFromItem();
                            if (fromItem instanceof Table) {
                                final String name = ((Table) fromItem).getName();
                                if (oldName.equals(name)) {
                                    // 如果表名匹配，则将旧表换成新表
                                    ((Table) fromItem).setName(newName);
                                    log.info("表替换,将 {} 替换为 {}", oldName, newName);
                                }
                            }
                        }
                    }
                    sqlStringBuilder.append(statement);
                }
            }
            return sqlStringBuilder.toString();
        } catch (Exception e) {
            log.error("表替换异常", e);
        }
        return sql;
    }
}
