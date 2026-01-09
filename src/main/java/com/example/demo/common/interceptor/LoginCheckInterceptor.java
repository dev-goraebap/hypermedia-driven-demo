package com.example.demo.common.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        String requestURI = request.getRequestURI();
        boolean isLoggedIn = session != null && session.getAttribute("loginUser") != null;
        boolean isLoginPage = "/login".equals(requestURI);

        // 로그인 페이지 + 이미 로그인됨 → 메인으로
        if (isLoginPage && isLoggedIn) {
            response.sendRedirect("/");
            return false;
        }

        // 로그인 페이지가 아님 + 로그인 안됨 → 로그인 페이지로
        if (!isLoginPage && !isLoggedIn) {
            response.sendRedirect("/login");
            return false;
        }

        return true;
    }
}
