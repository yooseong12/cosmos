package com.toy.cosmos.auth.exception.join;

import com.toy.cosmos.auth.value.AuthError;

public class MaximumExceededException extends JoinException {

    public MaximumExceededException(AuthError authError) {
        super(authError);
    }

}
