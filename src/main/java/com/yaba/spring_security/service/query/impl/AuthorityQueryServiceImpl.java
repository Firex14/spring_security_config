package com.yaba.spring_security.service.query.impl;


import com.yaba.spring_security.entity.UserAuthority;
import com.yaba.spring_security.enums.Authority;
import com.yaba.spring_security.exceptions.NotFoundException;
import com.yaba.spring_security.repository.UserAuthorityRepository;
import com.yaba.spring_security.service.query.AuthorityQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthorityQueryServiceImpl implements AuthorityQueryService {
    private final UserAuthorityRepository repository;
    @Override
    public UserAuthority findByAuthority(Authority authority) {
        return repository.findByAuthority(authority).orElseThrow(() -> new NotFoundException(String.format("ExceptionMessage.NOT_EXISTING_VALUE_EXCEPTION %s", authority)));
    }
}
