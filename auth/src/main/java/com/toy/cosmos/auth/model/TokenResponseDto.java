package com.toy.cosmos.auth.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TokenResponseDto {

    final String tokenType;

    final String accessToken;

    final Long expiresIn;

    final String refreshToken;

    final Long refreshTokenExpiresIn;

    final Boolean tmpPasswordUsed;

    final Boolean isJoin;

}
