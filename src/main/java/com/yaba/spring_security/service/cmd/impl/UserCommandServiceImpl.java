package com.yaba.spring_security.service.cmd.impl;

import com.yaba.spring_security.entity.UserAccount;
import com.yaba.spring_security.entity.UserAuthority;
import com.yaba.spring_security.enums.Authority;
import com.yaba.spring_security.exceptions.AlreadyExistsException;
import com.yaba.spring_security.exchange.request.UserCreationRequest;
import com.yaba.spring_security.repository.UserRepository;
import com.yaba.spring_security.service.cmd.UserCommandService;
import com.yaba.spring_security.service.query.AuthorityQueryService;
import com.yaba.spring_security.validation.RequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final RequestValidator requestValidator;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityQueryService authorityQueryService;


    @Override
    public UserAccount create(UserCreationRequest user) {
        requestValidator.check(user);

        if (repository.existsByUsernameIgnoreCase(user.getUsername())) {
            throw new AlreadyExistsException(user.getUsername());
        }

        UserAccount newUser = UserAccount.builder()
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .failedConnexionCount(0)
                .createdAt(ZonedDateTime.now())
                .build();

        UserAuthority authority = authorityQueryService.findByAuthority(Authority.USER);
        newUser.addAuthority(authority);

        newUser.setUp();

        requestValidator.check(newUser);

        return repository.save(newUser);
    }

    @Override
    public void refresh(UserAccount user) {
        repository.save(user);
    }
}
