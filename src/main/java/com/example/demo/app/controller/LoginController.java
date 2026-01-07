package com.example.demo.app.controller;

import com.example.demo.app.dto.LoginRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("login")
public class LoginController {
    @GetMapping
    public String index(
            HttpSession session
    ) {
        // 사용자가 인증세션이 있을경우 메인페이지로 리다이렉트
        Object loginUser = session.getAttribute("loginUser");
        if (loginUser != null) {
            return "redirect:/";
        }

        // 인증세션이 없으면 로그인 템플릿 응답
        return "pages/login";
    }

    @PostMapping
    public String login(
            @Valid @ModelAttribute LoginRequest dto,
            HttpSession session
    ) {
        // 아이디 비밀번호 검증
        var USERNAME = "testuser";
        var PASSWORD = "123123";
        if (!USERNAME.equals(dto.username()) || !PASSWORD.equals(dto.password())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "아이디 또는 비밀번호 불일치");
        }

        // 로그인 성공
        session.setAttribute("loginUser", "사용자");
        return "redirect:/";
    }
}
