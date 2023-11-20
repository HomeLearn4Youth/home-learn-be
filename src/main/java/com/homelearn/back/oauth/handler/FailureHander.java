package com.homelearn.back.oauth.handler;

import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class FailureHander implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.info("fail");
        responseFail(response);
    }
    public void responseFail(HttpServletResponse response) throws IOException {
        // 실패 응답 데이터 생성
        JSONObject responseData = new JSONObject();
        responseData.put("message", "Authentication fail");

        // JSON 형식 응답
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        response.getWriter().write(responseData.toJSONString());
    }
}
