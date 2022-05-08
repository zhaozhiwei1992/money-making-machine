package com.example.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 * @author zhaozhiwei
 * @version V1.0
 * @Title: null.java
 * @Package com.example.service
 * @Description: 统一获取基础数据的接口
 * 提供两种类型, select和tree类型封装
 * @date 2022/5/8 上午11:21
 */
@Service
@Transactional
public class CommonEleService {

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
                //                    把id作为value
                stringObjectMap.put("value", stringObjectMap.get("id"));
                //                    用户表没有name字段, 特殊处理下
                if (Objects.isNull(stringObjectMap.get("name"))) {
                    stringObjectMap.put("name", stringObjectMap.get("firstName"));
                }
                return stringObjectMap;
            })
            .collect(Collectors.toList());
        return collect;
    }

    //    转换成树形结构
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
}
