package com.yaba.spring_security.service.query;

import com.yaba.spring_security.entity.UserAccount;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

public interface UserQueryService extends UserDetailsService {

    UserAccount findById(UUID id);
}
