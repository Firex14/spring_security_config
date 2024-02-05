package com.yaba.spring_security.service.query.impl;

import com.yaba.spring_security.entity.UserAccount;
import com.yaba.spring_security.exceptions.BadCredentialsException;
import com.yaba.spring_security.exchange.request.AuthRequest;
import com.yaba.spring_security.security.CustomAuthenticationManager;
import com.yaba.spring_security.security.JwtUtils;
import com.yaba.spring_security.service.query.AuthService;
import com.yaba.spring_security.service.query.UserQueryService;
import com.yaba.spring_security.validation.RequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final CustomAuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final RequestValidator validator;
    private final UserQueryService userQueryService;

    @Override
    public String authenticate(AuthRequest request) {
        validator.check(request);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    request.username(), request.password()
            );

            Authentication authenticated = authenticationManager.authenticate(authentication);

            String uuid = authenticated.getPrincipal().toString();

            UserAccount user = userQueryService.findById(UUID.fromString(uuid));

            final String token = jwtUtils.generateToken(user);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            return token;

    }
}
