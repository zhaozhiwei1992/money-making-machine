package com.example.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Service;

@Service
public class SysCalcuFormulaService {

    /**
     * @param dataList : 待计算的数据, 数据要提前构建好,如果是表间计算，需要使用 "表名.字段" 作为key
     * @param formula  : 公式信息 key: xx字段 value: {x}+{b}/{c}
     * @data: 2022/6/15-下午11:02
     * @method: calculation
     * @return: void
     * @Description: 公式计算
     */
    public void calculation(List<Map<String, Object>> dataList, Map<String, String> formula) {
        //        1. 遍历数据, 每条数据进行公式计算
        for (Map<String, Object> data : dataList) {
            // 2. 遍历每一个公式, 通过数据进行公式填充
            for (Map.Entry<String, String> formulaMap : formula.entrySet()) {
                final String colCode = formulaMap.getKey();
                final String formulaStr = formulaMap.getValue();

                ExpressionParser parser = new SpelExpressionParser();
                TemplateParserContext parserContext = new TemplateParserContext();
                String format = parser.parseExpression(formulaStr, parserContext).getValue(data, String.class);

                final BigDecimal bigDecimal = parser.parseExpression(format).getValue(BigDecimal.class).setScale(0, RoundingMode.HALF_UP);
                data.put(colCode, bigDecimal);
            }
        }
    }
}
