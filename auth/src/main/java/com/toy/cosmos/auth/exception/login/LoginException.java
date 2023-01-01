package com.toy.cosmos.auth.exception.login;

import com.toy.cosmos.auth.exception.UserException;
import com.toy.cosmos.auth.value.Error;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginException extends UserException implements Serializable {

    public LoginException(Error error) {
        super(error);
    }

}
