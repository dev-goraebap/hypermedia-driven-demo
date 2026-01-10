package com.example.demo.app.domain;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Todo {

    private final String id;

    private final String content;

    Todo(String content) {
        this.id = UUID.randomUUID().toString();
        this.content = content;
    }
}
