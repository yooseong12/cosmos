package com.toy.cosmos.auth.exception;

import lombok.NoArgsConstructor;

import javax.naming.AuthenticationException;

@NoArgsConstructor
public class PasswordNotMatchedException extends AuthenticationException {
    public PasswordNotMatchedException(String explanation) {
        super(explanation);
    }
}
