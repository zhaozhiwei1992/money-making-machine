package com.example.service;

import io.swagger.annotations.ApiOperation;
import java.lang.annotation.Annotation;
import java.util.*;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @Title: UrlMappingService
 * @Package com/example/service/UrlMappingService.java
 * @Description: 初始化mapping及接口中文描述信息
 * @author zhaozhiwei
 * @date 2022/7/20 上午10:22
 * @version V1.0
 */
@Service
public class UrlMappingService {

    private final WebApplicationContext applicationContext;

    public UrlMappingService(WebApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    private final List<Map<String, String>> urlMappingList = new ArrayList<>();

    public List<Map<String, String>> getUrlMappingList() {
        return urlMappingList;
    }

    private final Map<String, String> urlMap = new HashMap<>();

    /**
     * @data: 2022/7/21-上午9:16
     * @User: zhaozhiwei
     * @method: getUrlMap

     * @return: java.util.Map<java.lang.String,java.lang.String>
     * @Description: 返回url和中文对照信息
     */
    public Map<String, String> getUrlMap() {
        return urlMap;
    }

    @PostConstruct
    public void init() throws InterruptedException {
        RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();

        for (Map.Entry<RequestMappingInfo, HandlerMethod> mappingInfoHandlerMethodEntry : map.entrySet()) {
            Map<String, String> resultMap = new LinkedHashMap<>();

            RequestMappingInfo requestMappingInfo = mappingInfoHandlerMethodEntry.getKey();
            HandlerMethod handlerMethod = mappingInfoHandlerMethodEntry.getValue();

            Annotation[] annotations = handlerMethod.getMethod().getDeclaredAnnotations();
            // 处理具体的方法信息
            for (Annotation annotation : annotations) {
                if (annotation instanceof ApiOperation) {
                    ApiOperation methodDesc = (ApiOperation) annotation;
                    String desc = methodDesc.value();
                    //接口描述
                    resultMap.put("desc", desc);
                }
            }
            PatternsRequestCondition p = requestMappingInfo.getPatternsCondition();
            for (String url : p.getPatterns()) {
                //请求URL
                resultMap.put("url", url);
            }
            // 保存url和描述的对照信息
            urlMap.put(resultMap.get("url"), resultMap.get("desc"));
            urlMappingList.add(resultMap);
        }
    }
}
