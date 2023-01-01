package com.toy.cosmos.auth.exception;

import com.toy.cosmos.auth.value.Error;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserException extends RuntimeException implements Serializable {
    @Getter
    Error error;

    public UserException(Error error) {
        this.error = error;
    }
}
