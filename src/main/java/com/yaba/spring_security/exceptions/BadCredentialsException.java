package com.yaba.spring_security.exceptions;


import static com.yaba.spring_security.messages.ErrorMessage.BAD_CREDENTIALS;

public class BadCredentialsException extends GlobalExceptionHandler {

    public BadCredentialsException() {
        super(String.format(BAD_CREDENTIALS));
    }

    public BadCredentialsException(String message) {
        super(String.format(message));
    }
    
}
