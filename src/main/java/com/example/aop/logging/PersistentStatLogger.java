package com.example.aop.logging;

import com.alibaba.druid.pool.DruidDataSourceStatLogger;
import com.alibaba.druid.pool.DruidDataSourceStatLoggerAdapter;
import com.alibaba.druid.pool.DruidDataSourceStatValue;
import com.alibaba.druid.stat.JdbcSqlStatValue;
import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.druid.util.JdbcSqlStatUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Title: PersistentStatLogger
 * @Package com/example/aop/logging/PersistentStatLogger.java
 * @Description:
 * 自定义实现druid的日志监控, 可以根据需要持久化, 默认的重启服务就没了
 * 这个是由druid根据配置参数间隔时间进行触发
 * https://www.bookstack.cn/read/Druid/374ebae44c3f5412.md
 * @author zhaozhiwei
 * @date 2022/5/31 下午10:32
 * @version V1.0
 */
public class PersistentStatLogger extends DruidDataSourceStatLoggerAdapter implements DruidDataSourceStatLogger {

    private static final Logger log = LoggerFactory.getLogger(PersistentStatLogger.class);

    @Override
    public void log(DruidDataSourceStatValue statValue) {
        Map<String, Object> map = new LinkedHashMap();
        map.put("dbType", statValue.getDbType());
        map.put("name", statValue.getName());

        ArrayList<Map<String, Object>> sqlList = new ArrayList();
        if (statValue.getSqlList().size() > 0) {
            LinkedHashMap sqlStatMap;
            for (Iterator var4 = statValue.getSqlList().iterator(); var4.hasNext(); sqlList.add(sqlStatMap)) {
                JdbcSqlStatValue sqlStat = (JdbcSqlStatValue) var4.next();
                sqlStatMap = new LinkedHashMap();
                sqlStatMap.put("sql", sqlStat.getSql());
                if (sqlStat.getExecuteCount() > 0L) {
                    sqlStatMap.put("executeCount", sqlStat.getExecuteCount());
                    sqlStatMap.put("executeMillisMax", sqlStat.getExecuteMillisMax());
                    sqlStatMap.put("executeMillisTotal", sqlStat.getExecuteMillisTotal());
                    sqlStatMap.put("executeHistogram", JdbcSqlStatUtils.rtrim(sqlStat.getExecuteHistogram()));
                    sqlStatMap.put("executeAndResultHoldHistogram", JdbcSqlStatUtils.rtrim(sqlStat.getExecuteAndResultHoldHistogram()));
                }
            }

            map.put("sqlList", sqlList);
        }
        String text = JSONUtils.toJSONString(map);
        log.info("自定义获取sql执行情况 {}", text);
    }
}
