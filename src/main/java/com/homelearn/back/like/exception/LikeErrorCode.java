package com.homelearn.back.like.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum LikeErrorCode {

    NOT_EXISTS_LIKE_ITEM("관심 아파트에 존재하지 않는 아파트 입니다.", NOT_FOUND),
    ALREADY_IN_LIKE_ITEM("이미 관심 아파트인 아파트 입니다.", BAD_REQUEST);

    private final String message;
    private HttpStatus httpStatus;

}
