package com.homelearn.back.group.exception;

import lombok.Getter;

@Getter
public class GroupException extends RuntimeException{

    private final GroupErrorCode errorCode;

    public GroupException(GroupErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
