package com.toy.cosmos.auth.exception.join;


import com.toy.cosmos.auth.value.AuthError;

public class NotMatchedAuthCodeException extends JoinException {

    public NotMatchedAuthCodeException() {
        super(AuthError.NOT_MATCHED_AUTH_CODE);
    }

}
