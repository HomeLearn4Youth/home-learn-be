package com.homelearn.back.dong.exception;

import lombok.Getter;

@Getter
public class DongException extends RuntimeException{

    private final DongErrorCode errorCode;

    public DongException(DongErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
