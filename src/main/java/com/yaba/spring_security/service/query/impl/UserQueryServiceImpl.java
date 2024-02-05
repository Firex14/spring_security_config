package com.yaba.spring_security.service.query.impl;

import com.yaba.spring_security.entity.UserAccount;
import com.yaba.spring_security.exceptions.NotFoundException;
import com.yaba.spring_security.repository.UserRepository;
import com.yaba.spring_security.service.query.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username).map(account -> (UserDetails)account)
                .orElseThrow(() -> new NotFoundException("Vos identifiants ne sont pas valides"));
    }

    @Override
    public UserAccount findById(UUID id) {
        return repository.findById(id).orElseThrow(
                ()-> new NotFoundException("l'uitlisateur n'existe pas")
        );
    }
}
