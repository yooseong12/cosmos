package com.toy.cosmos.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Error {

    NOT_FOUND_USER(1000, "user not found"),
    ALREADY_EXIST_USER(1001, "already exist user"),
    ALREADY_EXIST_USER_FRIEND(1002, "already exist user friend"),
    ACCESS_DENIED_EXCEPTION(1003, "access denied exception"),
    INVALID_PASSWORD(1000, "user not found"),

    NOT_FOUND_BOARD(2001, "board not found"),

    INTERNAL_SERVER_ERROR(9999, "internal server error");

    final Integer code;

    final String message;
}
