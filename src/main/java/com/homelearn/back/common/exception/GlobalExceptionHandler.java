package com.homelearn.back.common.exception;

import com.homelearn.back.common.util.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(JwtException.class)
    public ResponseEntity jwtExceptionHandler(JwtException e){
        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())
                .body(MessageUtil.error(e.getErrorCode().getHttpStatus(), e.getMessage()));
    }
}
