package com.toy.cosmos.api.exception.user;

import com.toy.cosmos.api.exception.Error;

public class AlreadyExistUserFriendException extends UserException {

    public AlreadyExistUserFriendException() {
        super(Error.ALREADY_EXIST_USER_FRIEND);
    }
}
