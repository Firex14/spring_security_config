package com.yaba.spring_security.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import java.util.Set;

public class EntityNotValidException extends ConstraintViolationException {
    public <T> EntityNotValidException(Set<ConstraintViolation<T>> violations) {
        super(violations);
    }
}
