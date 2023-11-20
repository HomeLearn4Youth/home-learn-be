package com.homelearn.back.oauth.dto;

import com.homelearn.back.oauth.OAuthProvider;
import com.homelearn.back.user.UserRole;
import com.homelearn.back.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Slf4j
@ToString
public class OAuthDto {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;

    @Builder
    public OAuthDto(Map<String, Object> attributes, String nameAttributeKey, String name, String email) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
    }

    public static OAuthDto of(String userNameAttributeName, Map<String, Object> attributes) {
        return ofNaver(userNameAttributeName, attributes);
    }

    private static OAuthDto ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        LinkedHashMap<String,String> response= (LinkedHashMap<String, String>) attributes.get("response");
        log.info(response.toString());
        return OAuthDto.builder()
                .name(response.get("name"))
                .email(response.get("email"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .role(UserRole.DDUBEOKY)
                .provider(OAuthProvider.NAVER)
                .build();
    }
}
