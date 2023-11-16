package com.homelearn.back.user.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RefreshToken {
    private String signature;
    private Long userId;

    @Builder
    public RefreshToken(String signature, Long userId) {
        this.signature = signature;
        this.userId=userId;
    }
}
