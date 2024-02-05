package com.yaba.spring_security.exceptions;

public class GlobalExceptionHandler extends RuntimeException{
    GlobalExceptionHandler() {

    }
    GlobalExceptionHandler(String message) {
        super(message);
    }
}
