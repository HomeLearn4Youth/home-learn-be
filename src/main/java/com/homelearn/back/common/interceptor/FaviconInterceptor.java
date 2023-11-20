package com.homelearn.back.common.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FaviconInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if("/favicon.ico".equals(request.getRequestURI())) {
            return false; // 요청 처리 중단
        }
        return true; // 그 외 요청은 계속 진행
    }
}
