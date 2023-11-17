package com.homelearn.back.group.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum GroupErrorCode {
    NOT_EXISTS_GROUP("존재하지 않는 그룹 입니다.", NOT_FOUND),
    NOT_EXISTS_GROUP_ITEM(" 그룹에 속해 있지 않는 아파트 입니다.", NOT_FOUND),
    ALREADY_IN_GROUP_ITEM("이미 그룹에 속해 있는 아파트 입니다.", BAD_REQUEST);

    private final String message;
    private HttpStatus httpStatus;
}
