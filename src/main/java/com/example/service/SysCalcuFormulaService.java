package com.example.service;

import cn.hutool.core.util.StrUtil;
import com.example.domain.SysFormulaTab;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Service;

@Service
public class SysCalcuFormulaService {

    /**
     * @param dataList    : 待计算的数据, 数据要提前构建好,如果是表间计算，需要使用 "表名.字段" 作为key
     * @param formulaList : 公式信息 key: xx字段 value: {x}+{b}/{c}
     * @data: 2022/6/15-下午11:02
     * @method: calculation
     * @return: void
     * @Description: 公式计算
     */
    public void calculation(List<Map<String, Object>> dataList, List<SysFormulaTab> formulaList) {
        //     1. 遍历数据, 每条数据进行公式计算
        for (Map<String, Object> data : dataList) {
            // 2. 遍历每一个公式, 进行排序, 并且舍如方式也按照实际配置来
            for (SysFormulaTab sysFormulaTab : formulaList) {
                final String colCode = sysFormulaTab.getColEname();
                final String formulaStr = sysFormulaTab.getCalFormula();

                ExpressionParser parser = new SpelExpressionParser();
                TemplateParserContext parserContext = new TemplateParserContext();
                String format = parser.parseExpression(formulaStr, parserContext).getValue(data, String.class);

                RoundingMode roundingMode;
                if (StrUtil.isNotEmpty(sysFormulaTab.getRoundType())) {
                    roundingMode = RoundingMode.valueOf(sysFormulaTab.getRoundType());
                } else {
                    roundingMode = RoundingMode.HALF_UP;
                }
                final BigDecimal bigDecimal = parser.parseExpression(format).getValue(BigDecimal.class).setScale(0, roundingMode);
                data.put(colCode, bigDecimal);
            }
        }
    }

    /**
     * @param dataList : 待计算的数据, 数据要提前构建好,如果是表间计算，需要使用 "表名.字段" 作为key
     * @param formula  :
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
        // TODO 这里可以增加数据转换逻辑, c1, c2转换为t0.c1, t0.c2方便配合公式计算, 返回数据时记得覆盖

        // 转换为基础公式
        this.transFormula(formula);

        final ExpressionParser parser = new SpelExpressionParser();
        final TemplateParserContext parserContext = new TemplateParserContext();

        // 多个线程进行公式计算
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
        executor.setKeepAliveTime(10, TimeUnit.SECONDS);
        executor.allowCoreThreadTimeOut(true);

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
                    // 标准字段应该去掉t0,
                    data.put(colCode.replace("t0.", ""), bigDecimal);
                });
            }
        }
    }

    /**
     * @data: 2022/8/23-下午3:01
     * @User: zhaozhiwei
     * @method: transFormula
     * @param formula :
     * @return: void
     * @Description: 嵌套公式, 转换为基础公式
     * 防止重复字段, 所以默认字段使用t0.
     */
    private void transFormula(Map<String, String> formula) {
        // 1. 公式排序, 2. 公式转换
        // t0:其实就是显示的结果， 界面显示去掉t0
        // 公式转换, 将公式列列参与计算的，先转换为数据列, 如 t0.c1, t0.c2, t0.c3为数据列,
        // t0.c4=t0.c1+t0.c2  t0.c5=t0.c2+t1.c3 t0.c6=t0.c4+t0.c5, 那么调整c6, t0.c6=(c1+c2)+(t0.c2+t1.c3)
        System.out.println("原公式: ");
        System.out.println(formula);

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

        System.out.println("转换后公式: ");
        System.out.println(formula);
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
                // formula.put("no-in-table", "#{['t1.a']}*#{['t2.b']}");
                // 替换字符串
                newFormulaStr = newFormulaStr.replaceAll("#\\{\\['" + formulaKey + "'\\]\\}", "(" + formula.get(formulaKey) + ")");

                // 替换后的可能还存在包含formula的部分, 继续替换
                return this.replaceFormulaStr(formula, newFormulaStr);
            }
        }
        return newFormulaStr;
    }
}
