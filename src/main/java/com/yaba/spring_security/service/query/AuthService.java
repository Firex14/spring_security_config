package com.yaba.spring_security.service.query;

import com.yaba.spring_security.exchange.request.AuthRequest;

public interface AuthService {

    String authenticate(AuthRequest request);

}
