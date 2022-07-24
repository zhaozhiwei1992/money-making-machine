package com.example.security;

import com.example.repository.UserRepository;
import com.example.security.jwt.JWTFilter;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class ExtInterfacePermissionConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final UserRepository userRepository;

    public ExtInterfacePermissionConfigurer(UserRepository tokenProvider) {
        this.userRepository = tokenProvider;
    }

    @Override
    public void configure(HttpSecurity http) {
        ExtInterfacePermissionFilter extInterfacePermissionFilter = new ExtInterfacePermissionFilter(userRepository);
        // 认证通过以后校验接口是否有权限
        http.addFilterAfter(extInterfacePermissionFilter, JWTFilter.class);
    }
}
