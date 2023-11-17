package com.homelearn.back.house.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum HouseErrorCode {

    NOT_EXISTS_HOUSE("존재하지 않은 아파트 입니다.", BAD_REQUEST);

    private final String message;
    private HttpStatus httpStatus;

}
