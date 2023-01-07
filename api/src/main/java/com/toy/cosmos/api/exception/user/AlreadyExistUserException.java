package com.toy.cosmos.api.exception.user;

import com.toy.cosmos.api.exception.Error;

public class AlreadyExistUserException extends UserException {

    public AlreadyExistUserException() {
        super(Error.ALREADY_EXIST_USER);
    }
}
