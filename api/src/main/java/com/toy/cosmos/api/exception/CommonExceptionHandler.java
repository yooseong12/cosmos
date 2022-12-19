package com.toy.cosmos.api.exception;

import com.toy.cosmos.api.exception.user.UserException;
import com.toy.cosmos.api.model.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = {"com.toy.cosmos.api.controller"})
public class CommonExceptionHandler {

    @ExceptionHandler(UserException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response<Void> userExceptionHandler(UserException e) {
        return Response.<Void>builder()
                .code(e.getError().code)
                .message(e.getError().getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String exceptionHandler(Exception e) {
        return "나중에하자 존나 귀찮다"; // todo: exception 처리 예정
    }

}
