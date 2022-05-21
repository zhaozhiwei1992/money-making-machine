package com.example.service;

import cn.hutool.core.util.StrUtil;
import com.example.aop.HttpServletRequestAspect;
import com.example.domain.DataPermission;
import com.example.domain.DataPermissionsRel;
import com.example.repository.DataPermissionRepository;
import com.example.repository.DataPermissionsRelRepository;
import com.example.repository.UserRepository;
import com.example.security.SecurityUtils;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhaozhiwei
 * @version V1.0
 * @Title: null.java
 * @Package com.example.service
 * @Description: 提供一些数据权限使用的封装
 *
 * 全部数据权限：即不做权限区分，记录都可查。
 * 机构数据权限：只能查看用户所在机构的数据, 一个人可以属于多个机构(单位/部门)
 * 机构及以下数据权限：可以查看用户所在机构及下属机构的数据
 * 仅本人数据权限：只能查看自己管辖的数据
 * 自定义数据权限：用于一个用户对应多个部门的场景
 *
 * @date 2022/5/15 下午3:10
 */

@Service
@Transactional
public class DataPermissionsService {

    private static final Logger log = LoggerFactory.getLogger(DataPermissionsService.class);

    private final DataPermissionRepository dataPermissionRepository;

    private final DataPermissionsRelRepository dataPermissionsRelRepository;

    private final UserRepository userRepository;

    public DataPermissionsService(
        DataPermissionRepository dataPermissionRepository,
        DataPermissionsRelRepository dataPermissionsRelRepository,
        UserRepository userRepository
    ) {
        this.dataPermissionRepository = dataPermissionRepository;
        this.dataPermissionsRelRepository = dataPermissionsRelRepository;
        this.userRepository = userRepository;
    }

    public Expression buildDataPermissionCondition(Table table) {
        // 根据Expression接口的规则，　构建where条件
        final StringValue stringValue = new StringValue("1");
        final EqualsTo equalsToCondition = new EqualsTo(stringValue, stringValue);

        Expression appendExpression = equalsToCondition;
        // 根据菜单id和当前用户角色，确定是否有权限
        final Object menuIdObj = HttpServletRequestAspect.get("menuid");
        final String loginName = SecurityUtils.getCurrentUserLogin().get();

        //        需要通过table判断，如果是权限表本身不去处理这些过滤, 避免stackoverflow, 死循环, repository去查询会出发到该逻辑
        //        系统表(sys_), jhi_表, 工作流(act_)表都不走数据权限
        if (
            !Objects.isNull(menuIdObj) &&
            !StrUtil.isEmpty(loginName) &&
            !table.getName().contains("sys_") &&
            !table.getName().contains("jhi_") &&
            !table.getName().contains("act_")
        ) {
            String menuId = String.valueOf(menuIdObj);

            // 1. 获取用户角色
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            final List<String> roleIds = authentication
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
            log.info("数据权限用户角色信息: {}, 菜单信息: {}", roleIds, menuId);
            // 2. 获取当前用户角色 + menuid组合所配置的权限
            //  多个权限取交集 不同的权限设计不同sql
            final List<DataPermissionsRel> byMenuIdAndRoleIdIn = dataPermissionsRelRepository.findAllByMenuIdAndRoleIdIn(menuId, roleIds);
            if (!byMenuIdAndRoleIdIn.isEmpty()) {
                // 3. 获取权限信息
                final List<Long> ruleIdList = byMenuIdAndRoleIdIn
                    .stream()
                    .map(m -> Long.parseLong(m.getRuleId()))
                    .collect(Collectors.toList());
                final List<DataPermission> allRuleList = dataPermissionRepository.findAllById(ruleIdList);
                final List<String> ruleCodeList = allRuleList.stream().map(m -> m.getCode()).collect(Collectors.toList());
                if (ruleCodeList.contains(Constants.ALL_DATA_PERMISSION)) {
                    appendExpression = equalsToCondition;
                } else {
                    //                    多个权限用or拼起来
                    final OrExpression orExpression = new OrExpression();
                    for (DataPermission dataPermission : allRuleList) {
                        final String code = dataPermission.getCode();
                        if (Constants.INSTITUTION_DATA_PERMISSION.equals(code)) {
                            //                            所属机构权限
                            //                            获取用户所属机构
                            orExpression.withRightExpression(new InExpression(this.getAliasColumn(table, "机构"), null));
                        }

                        if (Constants.INSTITUTION_WITH_FOLLOWING_DATA_PERMISSION.equals(code)) {
                            //                            所属机构及下级
                            orExpression.withRightExpression(new InExpression(this.getAliasColumn(table, "机构"), null));
                        }

                        if (Constants.PERSONAL_ONLY_DATA_PERMISSION.equals(code)) {
                            //                            仅自己权限
                            final Long id = userRepository.findOneByLogin(loginName).get().getId();
                            equalsToCondition.setLeftExpression(this.getAliasColumn(table, "creater"));
                            equalsToCondition.setRightExpression(new StringValue(id.toString()));
                            orExpression.withRightExpression(equalsToCondition);
                        }

                        //                      存在规则sql,解析成Expression形式, 规则sql,通过规则界面配置明细生成
                        if (StrUtil.isNotEmpty(dataPermission.getRuleSql())) {
                            final Expression expression;
                            try {
                                expression = CCJSqlParserUtil.parseCondExpression(dataPermission.getRuleSql());
                            } catch (JSQLParserException e) {
                                throw new RuntimeException(e);
                            }
                            orExpression.withRightExpression(expression);
                        }
                    }
                    appendExpression = new Parenthesis(orExpression);
                }
            }
        }
        return appendExpression;
    }

    private Column getAliasColumn(Table table, String aliasColumn) {
        StringBuilder column = new StringBuilder();
        if (null == table.getAlias()) {
            column.append(table.getName());
        } else {
            column.append(table.getAlias().getName());
        }
        column.append(".");
        column.append(aliasColumn);
        return new Column(column.toString());
    }

    static class Constants {

        public static final String ALL_DATA_PERMISSION = "all_data_permission";

        public static final String INSTITUTION_DATA_PERMISSION = "institution_data_permission";

        public static final String INSTITUTION_WITH_FOLLOWING_DATA_PERMISSION = "institution_with_following_data_permission";

        public static final String PERSONAL_ONLY_DATA_PERMISSION = "personal_only_data_permission";
    }
}
