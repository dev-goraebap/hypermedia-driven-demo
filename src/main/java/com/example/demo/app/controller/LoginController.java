package com.example.demo.app.controller;

import com.example.demo.app.dto.LoginRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("login")
public class LoginController {
    @GetMapping
    public String index() {
        return "pages/login";
    }

    @PostMapping
    public String login(
            @Valid @ModelAttribute LoginRequest dto,
            BindingResult bindingResult,
            HttpSession session,
            Model model
    ) {
        // 1. Bean Validation 에러 처리
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .map(e -> e.getField() + ": " + e.getDefaultMessage())
                    .toList();
            model.addAttribute("errors", errors);
            return "pages/login";
        }

        // 2. 비즈니스 검증
        try {
            authenticate(dto);
        } catch (ResponseStatusException e) {
            model.addAttribute("errors", List.of(Objects.requireNonNull(e.getReason())));
            return "pages/login";
        }

        // 3. 로그인 성공
        session.setAttribute("loginUser", "사용자");
        return "redirect:/";
    }

    private void authenticate(LoginRequest dto) {
        var USERNAME = "testuser";
        var PASSWORD = "123123";
        if (!USERNAME.equals(dto.username()) || !PASSWORD.equals(dto.password())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "아이디 또는 비밀번호 불일치"
            );
        }
    }
}
