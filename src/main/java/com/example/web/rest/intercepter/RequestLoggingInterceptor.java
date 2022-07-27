package com.example.web.rest.intercepter;

import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.json.JSONUtil;
import com.example.domain.RequestLogging;
import com.example.repository.RequestLoggingRepository;
import com.example.security.SecurityUtils;
import com.example.service.UrlMappingService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @Title: TraceIdInterceptor
 * @Package com/example/web/rest/intercepter/TraceIdInterceptor.java
 * @Description: 每次请求产生traceId方便后续追踪
 * @author zhaozhiwei
 * @date 2022/5/31 下午3:28
 * @version V1.0
 */
public class RequestLoggingInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(RequestLoggingInterceptor.class);

    private static final ThreadLocal<String> traceIdThreadLocal = new ThreadLocal<>();

    public static String getCurrentTraceId() {
        return traceIdThreadLocal.get();
    }

    @Autowired
    private RequestLoggingRepository requestLoggingRepository;

    @Autowired
    private UrlMappingService urlMappingService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
            for (MethodParameter methodParameter : methodParameters) {
                log.info(methodParameter.getParameterName());
            }
            log.info("handler method : {} \n", handler);

            //            记录客户端ip
            final String clientIP = ServletUtil.getClientIP(request);
            //            请求的地址
            final String requestURI = request.getRequestURI();
            //            生成traceId
            final String traceId = UUID.randomUUID().toString();
            //            *.log中使用
            MDC.put("traceId", traceId);
            traceIdThreadLocal.set(traceId);
            //            当前用户
            final String loginName = SecurityUtils.getCurrentUserLogin().orElse(null);
            //            当前时间
            final LocalDateTime now = LocalDateTime.now();
            final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            final String currentTime = now.format(dateTimeFormatter);

            //           异步线程记录到数据库
            final RequestLogging requestLogging = new RequestLogging();
            requestLogging.setTraceId(traceId);
            requestLogging.setLoginName(loginName);
            requestLogging.setRequestURI(requestURI);
            requestLogging.setClientIP(clientIP);
            requestLogging.setCurrentTime(currentTime);
            if (requestURI.startsWith("/api") && !requestURI.startsWith("/api/request/")) {
                //                requestLogging.setRequestName(urlMappingService.getUrlMap().get(requestURI));
                //                final Map<String, Object> parameterMap = this.getParameterMap(request);
                //                requestLogging.setParams(JSONUtil.toJsonStr(parameterMap));
                //                requestLogging.setSuccess("是");
                requestLoggingRepository.save(requestLogging);
            }
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        final String requestURI = request.getRequestURI();
        if (!Objects.isNull(ex) && requestURI.startsWith("/api") && !requestURI.startsWith("/api/request/")) {
            // 如果有异常， 标记接口请求失败
            final String traceId = traceIdThreadLocal.get();
            final Optional<RequestLogging> result = requestLoggingRepository.findOneByTraceId(traceId);
            if (result.isPresent()) {
                RequestLogging requestLogging = result.get();
                //                requestLogging.setSuccess("否");
                requestLoggingRepository.save(requestLogging);
            }
        }
        //  清理threadLocal
        traceIdThreadLocal.remove();
    }
}
