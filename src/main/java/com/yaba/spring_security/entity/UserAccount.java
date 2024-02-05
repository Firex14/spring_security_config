package com.yaba.spring_security.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yaba.spring_security.validation.PatternValidation;
import com.yaba.spring_security.validation.groups.Create;
import com.yaba.spring_security.validation.groups.Update;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import static com.yaba.spring_security.validation.messages.ErrorMessage.MALFORMED_FIELD;
import static com.yaba.spring_security.validation.messages.ErrorMessage.REQUIRED_FIELD;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserAccount implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = REQUIRED_FIELD, groups = { Create.class, Update.class })
    @Pattern(regexp = PatternValidation.EMAIL, message = MALFORMED_FIELD, groups = {Create.class, Update.class})
    @Column(nullable = false)
    private String username;

    @NotNull(message = REQUIRED_FIELD, groups = { Create.class, Update.class })
    @Pattern(regexp = PatternValidation.PASSWORD, message = MALFORMED_FIELD, groups = {Create.class, Update.class})
    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @NotNull(message = REQUIRED_FIELD, groups = { Create.class, Update.class })
    @JoinTable(name = "user_account_authority",
            joinColumns = @JoinColumn(name = "user_account_id"),
            inverseJoinColumns = @JoinColumn(name = "user_authority_id")
    )
    private Set<UserAuthority> authorities;

    @NotNull(message = REQUIRED_FIELD, groups = { Create.class, Update.class })
    @JsonFormat(pattern = PatternValidation.DATE)
    @Column(nullable = false)
    private ZonedDateTime createdAt;

    @NotNull(message = REQUIRED_FIELD, groups = {  Update.class })
    @JsonFormat(pattern = PatternValidation.DATE)
    private ZonedDateTime updatedAt;

    @NotNull(message = REQUIRED_FIELD, groups = { Create.class, Update.class })
    @Column(nullable = false)
    private boolean accountNonExpired;

    @NotNull(message = REQUIRED_FIELD, groups = { Create.class, Update.class })
    @Column(nullable = false)
    private boolean accountNonLocked;

    @NotNull(message = REQUIRED_FIELD, groups = { Create.class, Update.class })
    @Column(nullable = false)
    private boolean credentialsNonExpired;

    @NotNull(message = REQUIRED_FIELD, groups = { Create.class, Update.class })
    @Column(nullable = false)
    private  boolean enabled;

    private Integer failedConnexionCount;

    private ZonedDateTime lastFailedConnexionDate;

    private ZonedDateTime lastConnexionDate;

    public void addAuthority(UserAuthority authority){
        if(Objects.isNull(authorities) || authorities.isEmpty()){
            authorities = new HashSet<>();
        }

        authorities.add(authority);
    }

    public void setUp(){
        enabled = true;
        accountNonExpired = true;
        credentialsNonExpired = true;
        accountNonLocked = true;
        updatedAt = ZonedDateTime.now();
    }

    public void shutDown(){
        enabled = false;
        accountNonExpired = false;
        credentialsNonExpired = false;
        accountNonLocked = false;
        updatedAt = ZonedDateTime.now();
    }

    public void updateFailedConnexionCount(){
        if (failedConnexionCount == null){
            failedConnexionCount = 0;
        }

        if (failedConnexionCount > 5){
            shutDown();
            return;
        }

        failedConnexionCount++;
        lastFailedConnexionDate = ZonedDateTime.now();
    }

    public boolean isNotValid(){
        return !accountNonExpired || !accountNonLocked || !credentialsNonExpired || !enabled;
    }

}
