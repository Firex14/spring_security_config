package com.yaba.spring_security.security;

import com.yaba.spring_security.entity.UserAccount;
import com.yaba.spring_security.service.query.UserQueryService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class RequestFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final UserQueryService queryService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        jwtUtils.parseJwt(request).ifPresent(token -> {

            final String username = jwtUtils.getUsernameFromToken(token);

            UserAccount account = (UserAccount) queryService.loadUserByUsername(username);

            final boolean isValidToken = jwtUtils.validateToken(token, account);

            if (isValidToken) {

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        account.getUsername(), account.getPassword(), account.getAuthorities()
                );

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        });

        filterChain.doFilter(request, response);
    }
}
