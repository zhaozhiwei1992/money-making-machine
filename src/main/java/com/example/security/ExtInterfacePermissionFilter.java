package com.example.security;

import com.example.domain.User;
import com.example.repository.UserRepository;
import com.example.security.jwt.JWTFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.GenericFilterBean;

/**
 * @author zhaozhiwei
 * @version V1.0
 * @Title: UserInterfacePermissionServiceImpl
 * @Package com/example/security/UserInterfacePermissionServiceImpl.java
 * @Description: 只认证api接口, 非api的这里不校验
 * @date 2022/7/14 下午2:35
 */
@Component("extInterfacePermissionService")
public class ExtInterfacePermissionFilter extends GenericFilterBean {

    private final UserRepository userRepository;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    public ExtInterfacePermissionFilter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        // 这个方法只做外部接口授权认证
        final String requestURI = ((HttpServletRequest) request).getRequestURI();
        final Optional<String> currentUserLogin = SecurityUtils.getCurrentUserLogin();
        if (requestURI.startsWith("/api/ext") && currentUserLogin.isPresent()) {
            boolean hasPermission = false;

            String username = currentUserLogin.get();
            // 管理员全部放行
            if ("admin".equals(username)) {
                filterChain.doFilter(request, response);
            } else {
                // 获取登录用户信息
                final User user = userRepository.findOneByLogin(username).orElse(new User());
                //            if("system".equals(user.getAppid())){
                //                // 系统用户不校验该接口
                //                return true;
                //            }

                // 白名单被拦截
                Set<String> urls = new HashSet<>(Arrays.asList(AuthoritiesConstants.AUTH_WHITELIST));

                //获取数据库中用户接口配置, 属于该租户并且没有超时的
                //            List<AppInterfacePermisson> userInterfacePermissonList =
                //                    appInterfacePermissonRepository.findAllByAppid(user.getAppid());
                //            for (AppInterfacePermisson userInterfacePermisson : userInterfacePermissonList) {
                //                urls.add(userInterfacePermisson.getUrl());
                //            }

                for (String url : urls) {
                    if (antPathMatcher.match(url, requestURI)) {
                        hasPermission = true;
                        break;
                    }
                }

                if (hasPermission) {
                    filterChain.doFilter(request, response);
                } else {
                    throw new RuntimeException("接口" + requestURI + "未授权, 或权限已到期");
                }
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
