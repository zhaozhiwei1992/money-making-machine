package com.example.listener;

import cn.hutool.extra.servlet.ServletUtil;
import com.example.service.OnLineUserService;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.authentication.event.LogoutSuccessEvent;
import org.springframework.stereotype.Component;

/**
 * @author zhaozhiwei
 * @version V1.0
 * @Title: null.java
 * @Package com.example.listener
 * @Description: 监听认证成功失败事件
 * {@see org.springframework.context.ApplicationListener}
 * 登录成功或者退出维护系统用户信息
 * @date 2022/5/25 下午2:24
 */
@Component
public class ListenerAuthenticationEvent {

    private static final Logger log = LoggerFactory.getLogger(ListenerAuthenticationEvent.class);

    private OnLineUserService onLineUserService;

    private HttpServletRequest request;

    public ListenerAuthenticationEvent(OnLineUserService onLineUserService, HttpServletRequest request) {
        this.onLineUserService = onLineUserService;
        this.request = request;
    }

    @EventListener(classes = AuthenticationSuccessEvent.class)
    public void onSuccess(AuthenticationSuccessEvent successEvent) {
        log.info("{} 认证成功", successEvent.getAuthentication().getName());

        //        todo 获取客户端信息, 如果获取不到则调整在 com.example.web.rest.UserJWTController.authorize写入threadLocal这里获取
        final HashMap<String, Object> onLineUser = new HashMap<>();
        onLineUser.put("ip", ServletUtil.getClientIP(request));
        onLineUser.put("os", "");
        onLineUser.put("sessionId", "");
        onLineUser.put("userName", successEvent.getAuthentication().getName());
        onLineUser.put("browser", "");
        onLineUser.put("nowTime", successEvent.getTimestamp());
    }

    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent failureEvent) {
        System.out.println(failureEvent.getAuthentication().getName() + "认证失败，失败原因：" + failureEvent.getException().getMessage());
    }

    @EventListener
    public void onLogout(LogoutSuccessEvent logoutSuccessEvent) {
        log.info("{} 退出成功", logoutSuccessEvent.getAuthentication().getName());
    }
}
