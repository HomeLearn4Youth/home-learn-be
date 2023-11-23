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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        String accessToken=jwtUtils.issueAccessToken(user);
        String refreshToken= jwtUtils.issueRefreshToken(user);
        // 쿠키 생성
        Cookie cookieAccess = new Cookie("accessToken", accessToken);
        Cookie cookieRefresh = new Cookie("refreshToken", refreshToken);
//        cookieAccess.setHttpOnly(true); // XSS 공격 방지
//        cookieAccess.setSecure(true); // HTTPS 통신에서만 쿠키 전송
        cookieAccess.setPath("/"); // 쿠키 접근 경로 설정
        cookieRefresh.setPath("/"); // 쿠키 접근 경로 설정
        // cookie.setMaxAge(...); // 쿠키 유효 시간 설정 (선택사항)

        // 응답에 쿠키 추가
        response.addCookie(cookieAccess);
        response.addCookie(cookieRefresh);
        response.sendRedirect("http://localhost:5173/");
    }
}
