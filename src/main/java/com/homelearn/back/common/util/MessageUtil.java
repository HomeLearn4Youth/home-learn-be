package com.homelearn.back.common.util;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MessageUtil<T> {
    private HttpStatus httpStatus;
    private String responseMessage;
    private T dataBody;

    public static <T> MessageUtil<T> success(T data) {
        return MessageUtil.<T>builder()
                .httpStatus(HttpStatus.OK)
                .responseMessage("정상 작동 입니다.")
                .dataBody(data)
                .build();
    }
    public static <T> MessageUtil<T> success() {
        return MessageUtil.<T>builder()
                .httpStatus(HttpStatus.OK)
                .responseMessage("정상 작동 입니다.")
                .build();
    }

    public static MessageUtil<?> error(HttpStatus status, String errorMessage) {
        return MessageUtil.builder()
                .httpStatus(status)
                .responseMessage(errorMessage)
                .build();
    }
}
