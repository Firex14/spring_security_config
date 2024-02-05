package com.yaba.spring_security.controller.query;

import com.yaba.spring_security.exchange.request.AuthRequest;
import com.yaba.spring_security.service.query.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AuthController.AUTH_ROUTE)
@RequiredArgsConstructor
public class AuthController {
    public static final String AUTH_ROUTE = "/auth";
    private final AuthService authService;

    @PostMapping
    public ResponseEntity<String> auth(@RequestBody AuthRequest request){
        return new  ResponseEntity<>(authService.authenticate(request), HttpStatus.OK);
    }

}
