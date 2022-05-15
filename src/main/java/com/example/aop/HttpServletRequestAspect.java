package com.example.aop;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhaozhiwei
 * @version V1.0
 * @Title: null.java
 * @Package com.example.aop
 * @Description: 拦截所有resource, 请求参数写入到threadlocal中
 * 1. 获取menuid
 * @date 2022/5/15 下午10:21
 */
@Component
@Aspect
public class HttpServletRequestAspect {

    private static final Logger log = LoggerFactory.getLogger(HttpServletRequestAspect.class);

    @Autowired
    private HttpServletRequest request;

    @Pointcut("within(com.example.web.rest..*)")
    public void applicationPackagePointcut() {}

    @Before("applicationPackagePointcut()")
    public void before(JoinPoint joinPoint) {
        String tragetClassName = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();

        log.info("++++++++++++++++++++ before ++" + tragetClassName + "." + methodName + " 方法开始 ++++++++++++++++++++++++");
        final String menuid = request.getParameter("menuid");
        set("menuid", menuid);
        log.info("menuid: {}", menuid);
        log.info("++++++++++++++++++++ before ++ " + tragetClassName + "." + methodName + " 方法结束 ++++++++++++++++++++++++");
    }

    private static final ThreadLocal<Map<String, Object>> holder = new ThreadLocal<>();

    private static void set(String key, Object value) {
        getContext().put(key, value);
    }

    public static Object get(String key) {
        return getContext().get(key);
    }

    private static Map<String, Object> getContext() {
        Map map = holder.get();
        if (map == null) {
            map = new HashMap();
            holder.set(map);
        }
        return map;
    }
}
