package com.toy.cosmos.auth.exception.join;

import com.toy.cosmos.auth.value.Error;

public class MaximumExceededException extends JoinException {

    public MaximumExceededException(Error error) {
        super(error);
    }

}
