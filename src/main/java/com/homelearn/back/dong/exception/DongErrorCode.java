package com.homelearn.back.dong.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum DongErrorCode {
    NOT_EXISTS_CODE("존재하지 않는 지역입니다.", NOT_FOUND);

    private final String message;
    private HttpStatus httpStatus;
}
