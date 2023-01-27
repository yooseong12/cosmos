package com.toy.cosmos.auth.model;

import com.toy.cosmos.auth.value.Regex;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JoinVo {
    @Size(max = 255)
    @Pattern(regexp = Regex.EMAIL)
    String email;

    @Size(max = 255)
    String password;

    @NotNull
    @Size(max = 100)
    String name;

    @Size(max = 255)
    String nickname;

    @NotNull
    @Size(max = 20)
    @Pattern(regexp = Regex.PHONE)
    String tel;

    LocalDate birthDate;

    @NotNull
    @Builder.Default
    @ColumnDefault("true")
    Boolean marketingEmailReceived = true;

    @NotNull
    @Builder.Default
    @ColumnDefault("true")
    Boolean marketingSmsReceived = true;

    @Size(min = 6, max = 6)
    String authCode;

    String appleToken;
}