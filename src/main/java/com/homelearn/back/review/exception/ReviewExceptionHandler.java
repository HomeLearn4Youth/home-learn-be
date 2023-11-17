package com.homelearn.back.review.exception;


import com.homelearn.back.common.util.MessageUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ReviewExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<MessageUtil> ReviewExceptionHandler(ReviewException e) {
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(MessageUtil.error(e.getErrorCode().getHttpStatus(),e.getMessage()));
    }
}


