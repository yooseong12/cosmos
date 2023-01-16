package com.toy.cosmos.api.exception.user;

import com.toy.cosmos.api.exception.Error;

public class InvalidPasswordException extends UserException {

    public InvalidPasswordException() {
        super(Error.INVALID_PASSWORD);
    }
}
