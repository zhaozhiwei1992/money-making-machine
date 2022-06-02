package com.example.aop;

import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.druid.filter.FilterEventAdapter;
import com.alibaba.druid.proxy.jdbc.JdbcParameter;
import com.alibaba.druid.proxy.jdbc.ResultSetProxy;
import com.alibaba.druid.proxy.jdbc.StatementProxy;
import com.alibaba.druid.support.json.JSONWriter;
import com.example.domain.SlowSqlLogging;
import com.example.repository.SlowSqlLoggingRepository;
import com.example.web.rest.intercepter.RequestLoggingInterceptor;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.NClob;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author zhaozhiwei
 * @version V1.0
 * @Title: CustomDruidStatLogFilter
 * @Package com/example/aop/CustomDruidStatLogFilter.java
 * @Description: 自定义过滤器, 实时监控慢sql
 * <p>
 * 可以将traceid放入, 更加容易定位问题
 * @date 2022/6/1 下午4:25
 */
public class CustomDruidStatLogFilter extends FilterEventAdapter {

    private static final Logger log = LoggerFactory.getLogger(CustomDruidStatLogFilter.class);

    private static final Pattern LINE_BREAK = Pattern.compile("\n");
    private static final Pattern BLANKS = Pattern.compile(" +");
    private static final String BLANK = " ";

    /**
     * 开启状态
     */
    @Value("${logSwitch:true}")
    private boolean logSwitch;

    /**
     * 慢sql判断阈值（毫秒）
     */
    @Value("${spring.datasource.druid.filter.stat.slow-sql-millis:100}")
    private long slowSqlMillis;

    @Override
    protected void statementExecuteUpdateAfter(StatementProxy statement, String sql, int updateCount) {
        internalAfterStatementExecute(statement);
    }

    @Override
    protected void statementExecuteQueryAfter(StatementProxy statement, String sql, ResultSetProxy resultSet) {
        internalAfterStatementExecute(statement);
    }

    @Override
    protected void statementExecuteAfter(StatementProxy statement, String sql, boolean firstResult) {
        internalAfterStatementExecute(statement);
    }

    @Override
    protected void statementExecuteBatchAfter(StatementProxy statement, int[] result) {
        internalAfterStatementExecute(statement);
    }

    /**
     * 核心记录方法：判断记录慢sql
     */
    private void internalAfterStatementExecute(StatementProxy statement) {
        if (logSwitch) {
            if (statement.getSqlStat() != null) {
                long nanos = System.nanoTime() - statement.getLastExecuteStartNano();
                long millis = nanos / (1000 * 1000);
                if (millis >= slowSqlMillis) {
                    String slowParameters = buildSlowParameters(statement);
                    String sql = statement.getLastExecuteSql();
                    sql = LINE_BREAK.matcher(sql).replaceAll(BLANK);
                    sql = BLANKS.matcher(sql).replaceAll(BLANK);

                    final String currentTraceId = RequestLoggingInterceptor.getCurrentTraceId();
                    // 打印日志。还可自行替换为使用数据库等方式来保存，用于后续统计
                    //                    todo 输出es可识别形式日志, 进行慢sql日志收集
                    log.warn("custom slow sql [" + millis + "] millis, sql: [" + sql + "], params: " + slowParameters);

                    //                  数据库记录慢sql信息
                    final SlowSqlLogging slowSqlLogging = new SlowSqlLogging();
                    slowSqlLogging.setTraceId(currentTraceId);
                    final LocalDateTime now = LocalDateTime.now();
                    final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    slowSqlLogging.setCurrentTime(now.format(dateTimeFormatter));
                    slowSqlLogging.setSql(sql);
                    slowSqlLogging.setExecuteMillis(String.valueOf(millis));
                    slowSqlLogging.setExecuteParams(slowParameters);
                    final SlowSqlLoggingRepository slowSqlLoggingRepository = SpringUtil.getBean(SlowSqlLoggingRepository.class);
                    slowSqlLoggingRepository.save(slowSqlLogging);
                }
            }
        }
    }

    /**
     * 组装查询参数
     */
    private String buildSlowParameters(StatementProxy statement) {
        JSONWriter out = new JSONWriter();

        out.writeArrayStart();
        for (int i = 0, parametersSize = statement.getParametersSize(); i < parametersSize; ++i) {
            JdbcParameter parameter = statement.getParameter(i);
            if (i != 0) {
                out.writeComma();
            }
            if (parameter == null) {
                continue;
            }

            Object value = parameter.getValue();
            if (value == null) {
                out.writeNull();
            } else if (value instanceof String) {
                String text = (String) value;
                if (text.length() > 100) {
                    out.writeString(text.substring(0, 97) + "...");
                } else {
                    out.writeString(text);
                }
            } else if (value instanceof Number) {
                out.writeObject(value);
            } else if (value instanceof java.util.Date) {
                out.writeObject(value);
            } else if (value instanceof Boolean) {
                out.writeObject(value);
            } else if (value instanceof InputStream) {
                out.writeString("<InputStream>");
            } else if (value instanceof NClob) {
                out.writeString("<NClob>");
            } else if (value instanceof Clob) {
                out.writeString("<Clob>");
            } else if (value instanceof Blob) {
                out.writeString("<Blob>");
            } else {
                out.writeString('<' + value.getClass().getName() + '>');
            }
        }
        out.writeArrayEnd();

        return out.toString();
    }
}
