package com.toy.cosmos.auth.exception.login;

import com.toy.cosmos.auth.exception.UserException;
import com.toy.cosmos.auth.value.Error;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvalidTokenException extends UserException {

    String token;

    public InvalidTokenException(String token) {
        super(Error.INVALID_TOKEN);
    }

}
