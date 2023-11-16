package com.homelearn.back.common.filter;

import com.homelearn.back.common.config.SecurityConfig;
import com.homelearn.back.common.provider.JWTProvider;
import com.homelearn.back.common.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JWTProvider jwtProvider;
    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestPath = new UrlPathHelper().getPathWithinApplication(request);
        log.info("Request path: " + requestPath);

        // 요청 경로가 PERMITTED_URL에 포함되는지 확인
        boolean isPermittedPath = Arrays.stream(SecurityConfig.getPERMITTED_URL())
                .anyMatch(permittedPath -> requestPath.startsWith(permittedPath.replace("/**", "")));

        if (isPermittedPath) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = jwtUtils.getParseJwt(request.getHeader("Authorization"));
        if(token!=null&&jwtProvider.validateToken(token)){
            //토큰이 유효할 경우 토큰에서 Authentication 객체를 가져와 SecurityContext에 저장
            Authentication auth = jwtProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
            log.info("validation passed");
            filterChain.doFilter(request,response);
        }else {
            // 토큰이 유효하지 않을 경우 403 Forbidden 응답을 보냄
            log.info("Invalid token");
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid or Expired Token");
            return;
        }
    }


}
