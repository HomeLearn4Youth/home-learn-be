package com.homelearn.back.notice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum NoticeErrorCode {

    NOT_EXISTS_NOTICE("존재하지 않는 글 입니다.", NOT_FOUND),
    FORBIDDEN_NOTICE("접근 권한이 없습니다.", FORBIDDEN);

    private final String message;
    private HttpStatus httpStatus;

}
