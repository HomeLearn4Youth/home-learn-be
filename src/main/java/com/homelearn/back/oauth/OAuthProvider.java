package com.homelearn.back.oauth;

import lombok.Getter;

@Getter
public enum OAuthProvider {
    NAVER("NAVER");
    private final String provider;
    OAuthProvider(String provider){
        this.provider=provider;
    }
}
