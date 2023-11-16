package com.homelearn.back.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(JwtException.class)
    public ResponseEntity jwtExceptionHandler(JwtException e){
        log.debug("[JWT EXCEPTION]",e);
        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())
                .body(e.getMessage());
    }
}
