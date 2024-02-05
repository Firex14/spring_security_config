package com.yaba.spring_security.service.query;


import com.yaba.spring_security.entity.UserAuthority;
import com.yaba.spring_security.enums.Authority;

public interface AuthorityQueryService {
    UserAuthority findByAuthority(Authority authority);

}
