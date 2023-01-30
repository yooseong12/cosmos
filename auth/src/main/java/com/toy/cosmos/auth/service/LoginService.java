package com.toy.cosmos.auth.service;

import com.toy.cosmos.auth.config.TokenProvider;
import com.toy.cosmos.auth.exception.UnauthorizedException;
import com.toy.cosmos.auth.model.LoginVo;
import com.toy.cosmos.auth.model.TokenResponseDto;
import com.toy.cosmos.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final TokenProvider tokenProvider;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public User login(LoginVo loginVo) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginVo.getEmail(), loginVo.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        return (User) authentication.getPrincipal();
    }

    public TokenResponseDto generateTokenResponse(User user) {
        return tokenProvider.generateTokenResponse(user, true, false);
    }

    public Long getLoginUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            if (UsernamePasswordAuthenticationToken.class.isAssignableFrom(
                    Optional.ofNullable(authentication).orElseThrow(UnauthorizedException::new).getClass())) {
                return Long.valueOf(authentication.getName());
            }
        } catch (UnauthorizedException e) {
            throw e;
        }
        return null;
    }
}
