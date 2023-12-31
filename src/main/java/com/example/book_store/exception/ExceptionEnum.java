package com.example.book_store.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
@Getter
@RequiredArgsConstructor
public enum ExceptionEnum {
    ALREADY_EXIST(HttpStatus.CONFLICT),
    NOT_EXIST(HttpStatus.NOT_FOUND),
    IS_RESERVED(HttpStatus.FORBIDDEN),
    MAXIMUM_RESERVES_REACHED(HttpStatus.FORBIDDEN),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED);

    private final HttpStatus status;

}