package com.homelearn.back.report.exception;

import lombok.Getter;

@Getter
public class HouseException extends RuntimeException{
    private final HouseErrorCode errorCode;

    public HouseException(HouseErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}

