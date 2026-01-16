package com.example.demo.app.domain;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private User user;

    public User getUser() {
        return user;
    }

    @PostConstruct
    private void init() {
        user = new User("홍길동", "hong1234@example.com");
    }
}
