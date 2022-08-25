package com.example.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;

/**
 * @author zhaozhiwei
 * @version V1.0
 * @Title: JUnit5 Test Class.java.java
 * @Package com.example.service
 * @Description: TODO
 * @date 2022/8/25 上午11:34
 */
public class SysCalcuFormulaServiceTest {

    public static void main(String[] args) {
        //    @Test
        //    public void calculate() {
        // 如果map的value包含map的key, 则使用对应key的value替换包含部分
        final Map<String, String> formulaMap = new HashMap<>();
        // c1, c2, c3为基础数据列
        // c4, c5, c6为计算列
        formulaMap.put("t0.c4", "#{['t0.c1']}+#{['t0.c2']}");
        formulaMap.put("t0.c5", "#{['t0.c2']}+#{['t0.c3']}");

        // "#{['t1.a']}*#{['t2.b']}");
        // 修改为: (c1+c2)+((c1+c2)+(c2+c3))
        formulaMap.put("t0.c7", "#{['t0.c4']}+#{['t0.c5']}");
        // 修改为(c1+c2)+(c2+c3)
        formulaMap.put("t0.c6", "#{['t0.c4']}+#{['t0.c7']}");

        final List<Map<String, Object>> datas = new ArrayList<>();
        final Map<String, Object> map = new HashMap<>();
        map.put("c1", "1");
        map.put("c2", "2");
        map.put("c3", "3");
        datas.add(map);

        final SysCalcuFormulaService sysCalcuFormulaService = new SysCalcuFormulaService();

        final long l2 = System.currentTimeMillis();
        // 异步计算方式, 随着数据量增大, 性能不一定比顺序执行更好
        try {
            sysCalcuFormulaService.calculation(datas, formulaMap);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("异步计算耗时: " + (System.currentTimeMillis() - l2));

        final Map<String, Object> dataMap = datas.get(0);
        assertEquals(dataMap.get("c4"), new BigDecimal(3));
        assertEquals(dataMap.get("c5"), new BigDecimal(5));
        assertEquals(dataMap.get("c7"), new BigDecimal(8));
        assertEquals(dataMap.get("c6"), new BigDecimal(11));
    }
}
