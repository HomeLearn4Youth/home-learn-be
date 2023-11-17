package com.homelearn.back.review.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode {

    NOT_EXISTS_REVIEW("존재하지 않는 글 입니다.", NOT_FOUND);
    private final String message;
    private HttpStatus httpStatus;

}