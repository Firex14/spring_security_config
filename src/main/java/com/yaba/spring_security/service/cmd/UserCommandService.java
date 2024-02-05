package com.yaba.spring_security.service.cmd;

import com.yaba.spring_security.entity.UserAccount;
import com.yaba.spring_security.exchange.request.UserCreationRequest;

public interface UserCommandService {

    UserAccount create(UserCreationRequest user);
    void refresh(UserAccount user);

}
