package com.toy.cosmos.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Error {

    NOT_FOUND_USER(1000, "user not found"),
    ALREADY_EXIST_USER(1001, "already exist user"),
    ALREADY_EXIST_USER_FRIEND(1002, "already exist user friend"),

    NOT_FOUND_BOARD(2001, "board not found"),

    ACCESS_DENIED_EXCEPTION(3000, "access denied exception");

    final Integer code;

    final String message;
}
