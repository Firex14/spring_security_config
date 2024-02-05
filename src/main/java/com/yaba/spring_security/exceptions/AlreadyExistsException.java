package com.yaba.spring_security.exceptions;


import static com.yaba.spring_security.messages.ErrorMessage.ALREADY_EXISTS_FIELD;

public class AlreadyExistsException extends GlobalExceptionHandler {

    public AlreadyExistsException(String field) {
        super(String.format(ALREADY_EXISTS_FIELD, field));
    }
}
