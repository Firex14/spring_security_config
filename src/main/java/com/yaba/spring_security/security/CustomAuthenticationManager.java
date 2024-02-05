package com.yaba.spring_security.security;

import com.yaba.spring_security.entity.UserAccount;
import com.yaba.spring_security.exceptions.BadCredentialsException;
import com.yaba.spring_security.exceptions.MaxConnectionAttemptExceededException;
import com.yaba.spring_security.service.cmd.UserCommandService;
import com.yaba.spring_security.service.query.UserQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log4j2
public class CustomAuthenticationManager implements AuthenticationManager {

    private final UserQueryService userQueryService;
    private final UserCommandService userCommandService;
    private final PasswordEncoder encoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("Hi trying to login");

        UserAccount user = (UserAccount) userQueryService.loadUserByUsername(authentication.getPrincipal().toString());

        validate(authentication.getCredentials().toString(), user);

        Authentication authenticated = new UsernamePasswordAuthenticationToken(
                user.getId(), user.getPassword()
        );

        SecurityContextHolder.getContext().setAuthentication(authenticated);

        return authenticated;
    }

    private void validate(String givenPassword, UserAccount user){
        if (user.getFailedConnexionCount() > 5){
            throw new MaxConnectionAttemptExceededException("Compte Bloqué");
        }

        if (user.isNotValid()){
            throw new BadCredentialsException("Invalid");
        }
        if (!encoder.matches(givenPassword, user.getPassword())){

            user.updateFailedConnexionCount();

            throw new BadCredentialsException("Invalid");
        }
        user.setFailedConnexionCount(0);

        userCommandService.refresh(user);
    }
}
