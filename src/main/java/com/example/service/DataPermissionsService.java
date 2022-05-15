package com.example.service;

import com.example.repository.DataPermissionRepository;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import org.springframework.stereotype.Service;

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
public class DataPermissionsService {

    private final DataPermissionRepository dataPermissionRepository;

    public DataPermissionsService(DataPermissionRepository dataPermissionRepository) {
        this.dataPermissionRepository = dataPermissionRepository;
    }

    public Expression buildDataPermissionCondition(Table table) {
        // 根据Expression接口的规则，　构建where条件
        final StringValue stringValue = new StringValue("1");
        final EqualsTo dataRightCondition = new EqualsTo(stringValue, stringValue);

        Expression appendExpression = dataRightCondition;
        //      todo 根据菜单id和当前用户角色，确定是否有权限
        if ("t_example".equals(table.getName())) {
            dataRightCondition.setLeftExpression(this.getAliasColumn(table, "name"));
            dataRightCondition.setRightExpression(new StringValue("张三"));
            appendExpression = dataRightCondition;
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
