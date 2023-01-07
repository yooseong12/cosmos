package com.toy.cosmos.auth.exception.join;

import com.toy.cosmos.auth.exception.UserException;
import com.toy.cosmos.auth.value.Error;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class JoinException extends UserException implements Serializable {

    public JoinException(Error error) {
        super(error);
    }

}
