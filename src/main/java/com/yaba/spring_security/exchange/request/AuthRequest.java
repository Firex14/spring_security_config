package com.yaba.spring_security.exchange.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yaba.spring_security.validation.groups.Create;
import jakarta.validation.constraints.NotNull;

import static com.yaba.spring_security.validation.messages.ErrorMessage.REQUIRED_FIELD;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AuthRequest(
        @NotNull(message = REQUIRED_FIELD, groups = Create.class)
        String username,
        @NotNull(message = REQUIRED_FIELD, groups = Create.class)
        String password) {
}
