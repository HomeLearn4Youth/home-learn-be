package com.homelearn.back.notice.exception;


import com.homelearn.back.common.util.MessageUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NoticeExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<MessageUtil> NoticeExceptionHandler(NoticeException e) {
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(MessageUtil.error(e.getErrorCode().getHttpStatus(),e.getMessage()));
    }
}


