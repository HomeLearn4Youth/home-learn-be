package com.homelearn.back.qna.exception;

import lombok.Getter;

@Getter
public class QuestionException extends RuntimeException{
    private final QuestionErrorCode errorCode;

    public QuestionException(QuestionErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}

