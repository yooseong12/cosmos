package com.toy.cosmos.api.exception.user;

import com.toy.cosmos.api.exception.Error;

public class NotFoundUserException extends UserException {

    public NotFoundUserException() {
        super(Error.NOT_FOUND_USER);
    }
}
