package com.homelearn.back.oauth.handler;

import com.homelearn.back.common.util.JwtUtils;
import com.homelearn.back.oauth.OAuthProvider;
import com.homelearn.back.oauth.dto.OAuthDto;
import com.homelearn.back.user.UserService;
import com.homelearn.back.user.entity.User;
import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class SuccessHandler implements AuthenticationSuccessHandler {
    private final UserService userService;
    private final JwtUtils jwtUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("success");
        OAuth2User oAuth2User= (OAuth2User) authentication.getPrincipal();
        OAuthDto oAuthDto=OAuthDto.of(oAuth2User.getName(),oAuth2User.getAttributes());
        log.info("oauth service : "+oAuthDto.toString());
        User user=userService.findOrCreate(oAuthDto);
        user.toBuilder()
                .provider(OAuthProvider.NAVER)
                .build();
        log.info(user.toString());
        responseWithToken(user,response);
    }
    public void responseWithToken(User user,HttpServletResponse response) throws IOException {
        String accessToken=jwtUtils.issueAccessToken(user);
        String refreshToken= jwtUtils.issueRefreshToken(user);
        log.info(accessToken);
        log.info(refreshToken);
        // 성공 응답 데이터 생성
        JSONObject responseData = new JSONObject();
        response.addHeader("Authorization",accessToken);
        response.addHeader("refresh_token",refreshToken);

        responseData.put("message", "Authentication successful");

        // JSON 형식 응답
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);

        response.getWriter().write(responseData.toJSONString());
    }
}
