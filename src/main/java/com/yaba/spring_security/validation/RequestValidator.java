package com.yaba.spring_security.validation;

import com.yaba.spring_security.exceptions.EntityNotValidException;
import com.yaba.spring_security.validation.groups.Create;
import com.yaba.spring_security.validation.groups.ValidationGroup;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class RequestValidator {
    private final Validator validator;

    public <T> void check(T request){
        check(request, Create.class);
    }

    public <T> void check(T document, Class<? extends ValidationGroup> groups){
        Set<ConstraintViolation<T>> violations = validator.validate(document, groups);
        if (!violations.isEmpty()){
            throw new EntityNotValidException(violations);
        }
    }

    public <T> void check(List<T> requests, Class<? extends ValidationGroup> groups){
        Set<ConstraintViolation<T>> violations = new HashSet<>();
        if (nonNull(requests) && !requests.isEmpty()) {
            for (T request: requests){
                violations.addAll(validator.validate(request, groups));
            }
        }
        if (!violations.isEmpty()){
            throw new EntityNotValidException(violations);
        }
    }
}
