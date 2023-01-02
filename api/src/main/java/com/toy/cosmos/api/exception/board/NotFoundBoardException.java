package com.toy.cosmos.api.exception.board;

import com.toy.cosmos.api.exception.Error;

public class NotFoundBoardException extends BoardException {

    public NotFoundBoardException() {
        super(Error.NOT_FOUND_BOARD);
    }
}
