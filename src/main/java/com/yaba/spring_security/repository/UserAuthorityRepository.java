package com.yaba.spring_security.repository;

import com.yaba.spring_security.entity.UserAuthority;
import com.yaba.spring_security.enums.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserAuthorityRepository extends JpaRepository<UserAuthority, UUID> {
    Optional<UserAuthority> findByAuthority(Authority authority);

}
