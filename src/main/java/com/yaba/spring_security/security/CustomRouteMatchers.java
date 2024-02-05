package com.yaba.spring_security.security;

import com.yaba.spring_security.controller.cmd.UserCommandController;
import com.yaba.spring_security.controller.query.AuthController;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

@Component
public class CustomRouteMatchers extends RouteMatchers{
    public CustomRouteMatchers() {
        super(
                new AntPathRequestMatcher(AuthController.AUTH_ROUTE, POST),
                new AntPathRequestMatcher(UserCommandController.USER_ROUTE)
        );
    }
}
