package com.homelearn.back.oauth;

import com.homelearn.back.oauth.dto.OAuthDto;
import com.homelearn.back.user.UserRole;
import com.homelearn.back.user.UserService;
import com.homelearn.back.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomOauthService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserService userService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("user service");
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        log.info(registrationId);
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        OAuthDto attributes = OAuthDto.of(userNameAttributeName,oAuth2User.getAttributes());

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(UserRole.DDUBEOKY.getAuthRole())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

}
