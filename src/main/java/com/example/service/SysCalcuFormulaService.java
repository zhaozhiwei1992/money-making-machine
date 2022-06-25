package com.example.service;

import cn.hutool.core.util.StrUtil;
import com.example.domain.SysFormulaTab;
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
     * @param dataList    : 待计算的数据, 数据要提前构建好,如果是表间计算，需要使用 "表名.字段" 作为key
     * @param formulaList : 公式信息 key: xx字段 value: {x}+{b}/{c}, 排好顺序再传进来
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
}
