package com.homelearn.back.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum JwtErrorCode {

    EXPIRED_TOKEN("토큰이 만료되었습니다.", HttpStatus.UNAUTHORIZED),
    INVALID_TOKEN("사용할 수 없는 토큰 입니다.", HttpStatus.UNAUTHORIZED),
    TOKEN_SIGNATURE_ERROR("잘못된 jwt signature", HttpStatus.UNAUTHORIZED),
    NOT_SUPPORT_TOKEN("지원되지 않는 jwt 토큰", HttpStatus.UNAUTHORIZED),
    NO_TOKEN("토큰이 없습니다.", HttpStatus.UNAUTHORIZED);

    private final String message;
    private HttpStatus httpStatus;
}
