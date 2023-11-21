package com.homelearn.back.common.filter;

import com.homelearn.back.common.config.SecurityConfig;
import com.homelearn.back.common.exception.JwtErrorCode;
import com.homelearn.back.common.exception.JwtException;
import com.homelearn.back.common.provider.JWTProvider;
import com.homelearn.back.common.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
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
        boolean isPermittedPath = Arrays
                .stream(SecurityConfig.getPERMITTED_URL())
                .anyMatch(permittedPath -> requestPath.startsWith(
                        permittedPath.replace("/**", "")));

        if (isPermittedPath) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            final String token = jwtUtils.getParseJwt(request.getHeader("Authorization"));
            if (StringUtils.hasText(token)) {
                jwtProvider.validateToken(token);//유효하지 않으면 ex 이 발생한다.
                //토큰이 유효할 경우 토큰에서 Authentication 객체를 가져와 SecurityContext에 저장
                Authentication auth = jwtProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
                filterChain.doFilter(request,response);
            }else {
                throw new JwtException(JwtErrorCode.NO_TOKEN);
            }
        }catch (JwtException e){
            //토큰이 유효하지 않을 경우 각 에러 메시지를 보낸다.
            log.debug("[JWT Exception] httpCode = {} errorMessage = {}", e.getErrorCode(), e.getMessage());
            SecurityContextHolder.clearContext();
            response.sendError(e.getErrorCode().getHttpStatus().value(), e.getMessage());
        }
//        log.debug("Authorization pass");

    }


}
