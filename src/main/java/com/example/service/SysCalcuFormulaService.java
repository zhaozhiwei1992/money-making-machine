package com.example.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Service;

@Service
public class SysCalcuFormulaService {

    private static final Logger log = LoggerFactory.getLogger(SysCalcuFormulaService.class);

    /**
     * @param dataList : 待计算的数据, 数据要提前构建好,如果是表间计算，需要使用 "表名.字段" 作为key
     * @param formula  : 公式, 为防止冲突, 公式采用 表名.字段 格式, 表内公式使用t0.c1形式
     * @data: 2022/8/23-上午11:15
     * @User: zhaozhiwei
     * @method: calculation
     * @return: void
     * @Description: 异步公式计算
     * 采用CompletableFuture实现
     *
     * @see com.lx.demo.springbootel.service.CalcuFormulaPlusImpl#calculation
     */
    public void calculation(List<Map<String, Object>> dataList, Map<String, String> formula)
        throws ExecutionException, InterruptedException {
        // 1. 转换为基础公式
        formula = this.transFormula(formula);

        final ExpressionParser parser = new SpelExpressionParser();
        final TemplateParserContext parserContext = new TemplateParserContext();

        // 线程数为cpu*2
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

        // 1. 遍历数据, 每条数据进行公式计算
        for (Map<String, Object> data : dataList) {
            // 2. 遍历每一个公式, 通过数据进行公式填充
            for (Map.Entry<String, String> formulaMap : formula.entrySet()) {
                final String colCode = formulaMap.getKey();
                final String formulaStr = formulaMap.getValue();

                executor.execute(() -> {
                    String format = parser.parseExpression(formulaStr, parserContext).getValue(data, String.class);
                    final BigDecimal bigDecimal = parser
                        .parseExpression(format)
                        .getValue(BigDecimal.class)
                        .setScale(0, RoundingMode.HALF_UP);
                    // 计算结果塞入 数据中
                    data.put(colCode, bigDecimal);
                });
            }
        }

        executor.shutdown();
        try {
            //等待直到所有任务完成, 否则可能还没计算完成就去获取计算结果了
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            log.error("线程执行异常", e);
        }
    }

    /**
     * @param formula :
     * @data: 2022/8/23-下午3:01
     * @User: zhaozhiwei
     * @method: transFormula
     * @return: void
     * @Description: 嵌套公式, 转换为基础公式
     * 防止重复字段, 所以默认字段使用t0.
     *
     * 公式转换完成后, 计算表默认t0. 转换为字段形式
     */
    private Map<String, String> transFormula(Map<String, String> formula) {
        // 1. 公式排序, 2. 公式转换
        // t0:其实就是显示的结果， 界面显示去掉t0
        // 公式转换, 将公式列列参与计算的，先转换为数据列, 如 t0.c1, t0.c2, t0.c3为数据列,
        // t0.c4=t0.c1+t0.c2  t0.c5=t0.c2+t1.c3 t0.c6=t0.c4+t0.c5, 那么调整c6, t0.c6=(t0.c1+t0.c2)+(t0.c2+t1.c3)
        log.info("原公式:\n {}", formula);

        for (Map.Entry<String, String> formulaMap : formula.entrySet()) {
            final String oldFormulaStr = formulaMap.getValue();
            String newFormulaStr = formulaMap.getValue();

            // 重新计算公式值，一直到全部替换为数据列为止
            newFormulaStr = replaceFormulaStr(formula, newFormulaStr);

            if (!oldFormulaStr.equals(newFormulaStr)) {
                // 如果公式变化则替换map的值
                formulaMap.setValue(newFormulaStr);
            }
        }

        String prefix = "t0.";

        // 转换后的公式, 消除本表公式前缀
        final Map<String, String> transFormula = new HashMap<>();
        for (Map.Entry<String, String> entry : formula.entrySet()) {
            final String key = entry.getKey();
            final String value = entry.getValue();
            transFormula.put(key.replaceAll(prefix, ""), value.replaceAll(prefix, ""));
        }
        log.info("转换后公式:\n {}", formula);

        return transFormula;
    }

    /**
     * @data: 2022/8/24-上午10:09
     * @User: zhaozhiwei
     * @method: replaceFormulaStr
     * @param formula :
     * @param newFormulaStr :
     * @return: java.lang.String
     * @Description: 递归替换
     */
    private String replaceFormulaStr(Map<String, String> formula, String newFormulaStr) {
        for (String formulaKey : formula.keySet()) {
            if (newFormulaStr.contains(formulaKey)) {
                // "#{['t1.a']}*#{['t2.b']}");
                // 替换字符串
                newFormulaStr = newFormulaStr.replaceAll("#\\{\\['" + formulaKey + "'\\]\\}", "(" + formula.get(formulaKey) + ")");

                // 替换后的可能还存在包含formula的部分, 继续替换
                return this.replaceFormulaStr(formula, newFormulaStr);
            }
        }
        return newFormulaStr;
    }
}
