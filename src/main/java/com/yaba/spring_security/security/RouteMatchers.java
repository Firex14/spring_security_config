package com.yaba.spring_security.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.List;

public class RouteMatchers implements RequestMatcher {

    protected static final String POST = "POST";
    protected static final String GET = "GET";
    protected static final String PATCH = "PATCH";
    protected static final String DELETE = "DELETE";
    private final RequestMatcher delegate;

    public RouteMatchers(RequestMatcher... requests) {
        List<RequestMatcher> matchers = List.of(requests);
        delegate = new OrRequestMatcher(matchers);
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        return delegate.matches(request);
    }
}
