package com.example.demo.app.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String index(
            HttpSession session
    ) {
        // 사용자가 인증세션이 없을경우 로그인페이지로 리다이렉트
        Object loginUser = session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/login";
        }

        // 인증세션이 있을 경우 메인 템플릿 응답
        return "pages/main";
    }
}
