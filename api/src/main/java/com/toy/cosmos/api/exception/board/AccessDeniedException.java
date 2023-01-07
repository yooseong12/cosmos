package com.toy.cosmos.api.exception.board;

import com.toy.cosmos.api.exception.Error;

public class AccessDeniedException extends BoardException {

    public AccessDeniedException() {
        super(Error.ACCESS_DENIED_EXCEPTION);
    }
}
