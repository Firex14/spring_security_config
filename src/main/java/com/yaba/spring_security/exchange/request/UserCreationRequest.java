package com.yaba.spring_security.exchange.request;

import com.yaba.spring_security.validation.PatternValidation;
import com.yaba.spring_security.validation.groups.Create;
import com.yaba.spring_security.validation.groups.Update;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import static com.yaba.spring_security.validation.messages.ErrorMessage.MALFORMED_FIELD;
import static com.yaba.spring_security.validation.messages.ErrorMessage.REQUIRED_FIELD;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserCreationRequest {

    @NotNull(message = REQUIRED_FIELD, groups = { Create.class, Update.class })
    @Pattern(regexp = PatternValidation.EMAIL, message = MALFORMED_FIELD, groups = {Create.class, Update.class})
    private String username;

    @NotNull(message = REQUIRED_FIELD, groups = { Create.class, Update.class })
    @Pattern(regexp = PatternValidation.PASSWORD, message = MALFORMED_FIELD, groups = {Create.class, Update.class})
    private String password;

}
