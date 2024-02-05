package com.yaba.spring_security.exceptions;


public class TokenException extends GlobalExceptionHandler {
    public TokenException(String message) {
        super(String.format(message));
    }

}
