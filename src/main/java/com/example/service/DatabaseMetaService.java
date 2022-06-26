package com.example.service;

import cn.hutool.db.meta.MetaUtil;
import com.example.domain.TaskParam;
import com.example.listener.QuartJobListener;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import org.quartz.*;
import org.springframework.stereotype.Component;

@Component
public class DatabaseMetaService {

    private final DataSource dataSource;

    public DatabaseMetaService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private List<String> tables;

    private final Map<String, List> tableColumnsMap = new HashMap<>();

    public List<String> getTables() {
        return tables;
    }

    public Map<String, List> getTableColumnsMap() {
        return tableColumnsMap;
    }

    @PostConstruct
    public void init() throws InterruptedException {
        // 1. 初始化所有表信息
        tables = MetaUtil.getTables(dataSource);
        // 2. 初始化所有列信息
        for (String tableName : tables) {
            List<String> columns;
            columns = Arrays.asList(MetaUtil.getColumnNames(dataSource, tableName));
            tableColumnsMap.put(tableName, columns);
        }
    }
}
