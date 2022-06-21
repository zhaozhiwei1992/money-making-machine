package com.example.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.example.aop.CustomStatementInspector;
import com.example.aop.MetadataExtractorIntegrator;
import com.example.domain.EleUnion;
import com.example.repository.EleUnionRepository;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.hibernate.boot.model.relational.Namespace;
import org.hibernate.mapping.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author zhaozhiwei
 * @version V1.0
 * @Title: null.java
 * @Package com.example.service
 * @Description:
 * 统一获取基础数据的接口
 * 提供两种类型, select和tree类型封装
 * @date 2022/5/8 上午11:21
 */
@Service
@Transactional
public class CommonEleService {

    private static final Logger log = LoggerFactory.getLogger(CommonEleService.class);

    private final EleUnionRepository eleUnionRepository;

    public CommonEleService(EleUnionRepository eleUnionRepository) {
        this.eleUnionRepository = eleUnionRepository;
    }

    /**
     * @data: 2022/6/17-下午3:06
     * @User: zhaozhiwei
     * @method: findMapping
     * @param beanName :
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Description: 基础要素统一规范，只能通过ele的方式来引入,不再支持bean的方式(太黑盒)
     */
    public List<Map<String, Object>> findMapping(String beanName) {
        if (StrUtil.isEmpty(beanName)) {
            throw new RuntimeException("请传入bean名");
        }

        final Object bean = SpringUtil.getBean(beanName);
        //      固定数据获取方式, findAll
        final List<Object> findAll = ReflectUtil.invoke(bean, "findAll");

        //      转换为mapping需要格式
        final List<Map<String, Object>> collect = findAll
            .stream()
            .map(obj -> {
                final Map<String, Object> stringObjectMap = BeanUtil.beanToMap(obj);
                //   把id作为value
                stringObjectMap.put("value", stringObjectMap.get("id"));
                //   用户表没有name字段, 特殊处理下
                if (Objects.isNull(stringObjectMap.get("name"))) {
                    stringObjectMap.put("name", stringObjectMap.get("firstName"));
                }
                return stringObjectMap;
            })
            .collect(Collectors.toList());
        return collect;
    }

    /**
     * @data: 2022/6/17-下午3:00
     * @User: zhaozhiwei
     * @method: findTreeMapping
     * @param beanName :
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Description: 转成树形结构
     */
    public List<Map<String, Object>> findTreeMapping(String beanName) {
        if (StrUtil.isEmpty(beanName)) {
            throw new RuntimeException("请传入bean名");
        }

        final Object bean = SpringUtil.getBean(beanName);
        //      固定数据获取方式, findAll
        final List<Object> findAll = ReflectUtil.invoke(bean, "findAll");

        //      转换为mapping需要格式
        final List<Map<String, Object>> collect = findAll
            .stream()
            .map(obj -> {
                final Map<String, Object> stringObjectMap = BeanUtil.beanToMap(obj);
                //                    把id作为value
                stringObjectMap.put("value", stringObjectMap.get("id"));
                return stringObjectMap;
            })
            .collect(Collectors.toList());
        return collect;
    }

    /**
     * @data: 2022/6/17-下午3:09
     * @User: zhaozhiwei
     * @method: findAllEleCategory

     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Description: 获取所有的基础要素分类
     * 一部分通过union表获取
     * 另外的需要查询ele_开头的表名进行收集
     */
    public List<Map<String, Object>> findAllEleCategory() {
        final List<Map<String, Object>> maps = new ArrayList<>();
        //1. 查询所有ele_union中数据, 然后提取eleCatcode/name
        final List<EleUnion> eleUnionAll = eleUnionRepository.findAll();
        // 去重
        Set<EleUnion> eleUnionTreeSet = new TreeSet<>(Comparator.comparing(EleUnion::getEleCatCode));
        eleUnionTreeSet.addAll(eleUnionAll);

        // 每种数据，只保留一条作为左侧数据源
        eleUnionTreeSet.forEach(eleUnion -> {
            maps.add(BeanUtil.beanToMap(eleUnion));
        });

        //2. 查询数据库中除 ele_union外所有ele_开头的表, 分别查询其中eleCatcode/name, 只需每个查一条就行
        final List<String> allTableNamesStartWithEle = this.findAllTableNamesStartWithEle();
        for (String tableName : allTableNamesStartWithEle) {
            if ("ele_union".equals(tableName) || "ele_leavetype".equals(tableName)) {
                continue;
            }

            Map<String, String> map = new HashMap<>();
            map.put("oldName", "ele_union");
            map.put("newName", tableName);
            CustomStatementInspector.replaceTable.set(map);

            final Page<EleUnion> all = eleUnionRepository.findAll(PageRequest.of(1, 1));
            final List<EleUnion> content = all.getContent();
            if (content.size() > 0) {
                maps.add(BeanUtil.beanToMap(content.get(0)));
            }
        }
        return maps;
    }

    /**
     * 获取所有基础要素表信息
     * 参考: https://vladmihalcea.com/how-to-get-access-to-database-table-metadata-with-hibernate-5/
     * @return
     * @throws SQLException
     */
    public List<String> findAllTableNamesStartWithEle() {
        final List<String> tableNameList = new ArrayList<>();
        for (Namespace namespace : MetadataExtractorIntegrator.INSTANCE.getDatabase().getNamespaces()) {
            for (Table table : namespace.getTables()) {
                if (table.getName().startsWith("ele_")) {
                    tableNameList.add(table.getName());
                }
                //                查列信息
                //                log.info( "Table {} has the following columns: {}",
                //                    table,
                //                    StreamSupport.stream(
                //                            Spliterators.spliteratorUnknownSize(
                //                                table.getColumnIterator(),
                //                                Spliterator.ORDERED
                //                            ),
                //                            false
                //                        )
                //                        .collect( Collectors.toList())
                //                );
            }
        }
        return tableNameList;
    }

    /**
     * @data: 2022/6/20-下午11:04
     * @User: zhaozhiwei
     * @method: findElementInfoByEleCatCode
     * @param eleCatCode :
     * @return: java.util.List<com.example.domain.EleUnion>
     * @Description: 根据eleCatCode获取完整的基础信息列表
     */
    public List<EleUnion> findElementInfoByEleCatCode(String eleCatCode) {
        //1. eleCatCode直接存在于ele_union表
        List<EleUnion> byEleCatCode = new ArrayList<>();

        byEleCatCode = eleUnionRepository.findByEleCatCode(eleCatCode);

        //2. eleCatCode是表明的一部分如 ele_eleCatCode(约定)
        if (byEleCatCode.size() < 1) {
            //            如果汇总表没有则查询单表
            final List<String> allTableNamesStartWithEle = this.findAllTableNamesStartWithEle();
            //            判断表存在
            if (allTableNamesStartWithEle.contains(eleCatCode)) {
                Map<String, String> map = new HashMap<>();
                map.put("oldName", "ele_union");
                map.put("newName", "ele_" + eleCatCode);
                CustomStatementInspector.replaceTable.set(map);

                byEleCatCode = eleUnionRepository.findByEleCatCode(eleCatCode);
            }
        }

        return byEleCatCode;
    }
}
