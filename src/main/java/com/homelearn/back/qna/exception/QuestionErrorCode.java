package com.homelearn.back.qna.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@Getter
@AllArgsConstructor
public enum QuestionErrorCode {

    NOT_EXISTS_QUESTION("존재하지 않는 질문입니다.", BAD_REQUEST),
    FORBIDDEN_QUESTION("권한이 없습니다.", FORBIDDEN);
    private final String message;
    private HttpStatus httpStatus;
}
