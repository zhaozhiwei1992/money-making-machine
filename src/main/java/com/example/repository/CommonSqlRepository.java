package com.example.repository;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * @Title: CommonSqlRepository
 * @Package com/example/repository/CommonSqlRepository.java
 * @Description: 纯sql方式的数据操作集合
 * @author zhaozhiwei
 * @date 2022/6/17 上午10:45
 * @version V1.0
 */
@Repository
public class CommonSqlRepository extends JdbcDaoSupport {

    public CommonSqlRepository(JdbcTemplate jdbcTemplate) {
        super.setJdbcTemplate(jdbcTemplate);
    }

    /**
     *
     * @param sql
     * @param o（不可传null）
     * @return
     */
    public List queryForList(String sql, Object o) {
        return this.getJdbcTemplate().queryForList(sql, o.getClass());
    }

    public List queryForList(String sql) {
        return this.getJdbcTemplate().queryForList(sql);
    }

    public int update(String sql) {
        return this.getJdbcTemplate().update(sql);
    }

    public void execute(String sql) {
        this.getJdbcTemplate().execute(sql);
    }

    public int queryForInt(String sql) {
        //single query expected.
        Number number = this.getJdbcTemplate().queryForObject(sql, Integer.class);
        return number != null ? number.intValue() : 0;
    }

    public int queryForTotal(String sql) {
        List list = this.queryForList(sql);
        return list != null ? list.size() : 0;
    }

    public void insertDatas(String tableCode, List<Map> lists) {
        List<Map<String, Object>> columnSql =
            this.getJdbcTemplate().queryForList("select t.column_name from user_tab_columns t where t.table_name = '" + tableCode + "'");
        List<String> columns = new ArrayList<>();
        for (Map tmp : columnSql) {
            for (Object o : tmp.entrySet()) {
                Map.Entry entry = (Map.Entry) o;
                columns.add(entry.getValue() + "");
            }
        }

        StringBuilder vchsql = new StringBuilder();
        StringBuilder valuesql = new StringBuilder();
        vchsql.append("insert into ").append(tableCode).append("(");
        List list = new ArrayList();
        for (String i : columns) {
            vchsql.append(i).append(",");
            valuesql.append("?,");
        }
        vchsql.delete(vchsql.length() - 1, vchsql.length());
        vchsql.append(")values(");
        vchsql.append(valuesql.delete(valuesql.length() - 1, valuesql.length()));
        vchsql.append(")");
        for (Map id : lists) {
            List listDto = new ArrayList();
            for (String key : columns) {
                Object o = id.get(key.toLowerCase());
                if (o instanceof Enum) {
                    listDto.add(o.toString());
                } else {
                    listDto.add(o);
                }
            }
            list.add(listDto);
        }
        try {
            this.getJdbcTemplate()
                .batchUpdate(
                    vchsql.toString(),
                    new BatchPreparedStatementSetter() {
                        @Override
                        public int getBatchSize() {
                            return list.size();
                        }

                        @Override
                        public void setValues(PreparedStatement ps, int i) throws SQLException {
                            List params = (List) list.get(i);
                            for (int j = 0; j < params.size(); j++) {
                                Object o = params.get(j);
                                setValues2Type(ps, o, j);
                            }
                        }
                    }
                );
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void deleteBySql(String tablecode, String whereSql) {
        String delSql = "delete from " + tablecode + " t " + (whereSql != null && !"".equals(whereSql) ? whereSql : "");
        this.getJdbcTemplate().execute(delSql);
    }

    private void setValues2Type(PreparedStatement ps, Object o, int index) throws SQLException {
        if (null == o) {
            ps.setNull(index + 1, 0);
        } else if (o instanceof String) {
            ps.setString(index + 1, String.valueOf(o));
        } else if (o instanceof Date) {
            ps.setDate(index + 1, (java.sql.Date) o);
        } else if (o instanceof Integer) {
            ps.setInt(index + 1, (Integer) o);
        } else if (o instanceof BigDecimal) {
            ps.setBigDecimal(index + 1, (BigDecimal) o);
        } else if (o instanceof Number) {
            ps.setBigDecimal(index + 1, new BigDecimal(o.toString()));
        } else if (o instanceof Clob) {
            ps.setClob(index + 1, (Clob) o);
        } else if (o instanceof Blob) {
            ps.setBlob(index + 1, (Blob) o);
        } else {
            throw new RuntimeException("参数" + index + "类型未知：" + o.getClass());
        }
    }
}
