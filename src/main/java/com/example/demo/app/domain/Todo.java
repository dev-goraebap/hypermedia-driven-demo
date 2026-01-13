package com.example.demo.app.domain;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Todo {

    private final String id;

    private String content;

    Todo(String content) {
        this.id = UUID.randomUUID().toString();
        this.content = content;
    }

    public void update(String content) {
        this.content = content;
    }
}
