package com.toy.cosmos.api.exception.board;

import com.toy.cosmos.api.exception.Error;
import lombok.Getter;

public class BoardException extends RuntimeException {

    @Getter
    final Error error;

    public BoardException(Error error) {
        super(error.getMessage());
        this.error = error;
    }

}
