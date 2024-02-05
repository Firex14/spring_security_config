package com.yaba.spring_security.entity;

import com.yaba.spring_security.enums.Authority;
import com.yaba.spring_security.validation.groups.Create;
import com.yaba.spring_security.validation.groups.Update;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.UUID;

import static com.yaba.spring_security.validation.messages.ErrorMessage.REQUIRED_FIELD;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@ToString
public class UserAuthority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Getter
    private UUID id;

    @NotNull(message = REQUIRED_FIELD, groups = { Create.class, Update.class })
    @Column(nullable = false, unique = true)
    @Setter
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Override
    public String getAuthority() {
        return authority.name();
    }
}
