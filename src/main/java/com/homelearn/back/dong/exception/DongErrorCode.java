package com.homelearn.back.dong.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum DongErrorCode {
    NOT_EXISTS_CODE("잘못된 지역 코드 입니다.", BAD_REQUEST);

    private final String message;
    private HttpStatus httpStatus;
}
