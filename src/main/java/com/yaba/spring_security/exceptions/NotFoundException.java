package com.yaba.spring_security.exceptions;

import static com.yaba.spring_security.messages.ErrorMessage.NOT_FOUND_FIELD;

public class NotFoundException extends GlobalExceptionHandler {

    public NotFoundException() {
        super();
    }
    public NotFoundException(String field) {
        super(String.format(NOT_FOUND_FIELD, field));
    }
}
