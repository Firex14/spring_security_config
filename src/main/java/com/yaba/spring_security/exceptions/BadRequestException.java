package com.yaba.spring_security.exceptions;

public class BadRequestException extends GlobalExceptionHandler {
    public BadRequestException(String message, String field) {
        super(String.format(message, field));
    }

}
