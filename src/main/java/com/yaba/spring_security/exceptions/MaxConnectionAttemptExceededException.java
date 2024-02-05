package com.yaba.spring_security.exceptions;

public class MaxConnectionAttemptExceededException extends GlobalExceptionHandler{
    public MaxConnectionAttemptExceededException() {
    }

    public MaxConnectionAttemptExceededException(String message) {
        super(message);
    }
}
