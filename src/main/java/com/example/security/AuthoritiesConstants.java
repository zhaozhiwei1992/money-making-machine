package com.example.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String USER = "ROLE_USER";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    public static final String[] AUTH_WHITELIST = {
        //            静态资源白名单
        "/app/**/*.{js,html}",
        //            url 白名单
        "/api/ext/authenticate",
        "/api/authenticate",
        // other public endpoints of your API may be appended to this array
    };

    private AuthoritiesConstants() {}
}
