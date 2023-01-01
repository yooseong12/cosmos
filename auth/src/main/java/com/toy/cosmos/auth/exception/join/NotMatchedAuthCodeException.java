package com.toy.cosmos.auth.exception.join;


import com.toy.cosmos.auth.value.Error;

public class NotMatchedAuthCodeException extends JoinException {

    public NotMatchedAuthCodeException() {
        super(Error.NOT_MATCHED_AUTH_CODE);
    }

}
