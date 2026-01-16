package com.example.demo.app.controller;

import com.example.demo.app.domain.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("me")
    public String show(Model model) {
        var user = userService.getUser();
        model.addAttribute("user", user);
        return "pages/users/show";
    }
}
