package com.homelearn.back.oauth.dto;

import com.homelearn.back.oauth.OAuthProvider;
import com.homelearn.back.user.UserRole;
import com.homelearn.back.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
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
        return OAuthDto.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
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
