package com.yaba.spring_security.controller.cmd;

import com.yaba.spring_security.entity.UserAccount;
import com.yaba.spring_security.exchange.request.UserCreationRequest;
import com.yaba.spring_security.service.cmd.UserCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UserCommandController.USER_ROUTE)
@RequiredArgsConstructor
public class UserCommandController {

    public static final String USER_ROUTE = "/cmd/users";
    private final UserCommandService service;

    @PostMapping
    public ResponseEntity<UserAccount> create(@RequestBody UserCreationRequest request){
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

}
