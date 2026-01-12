package com.example.demo.app.domain;

import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

    private final List<Todo> todoList = new ArrayList<>();

    public List<Todo> getTodoList() {
        return todoList;
    }

    public boolean checkDuplicate(@NotBlank String content) {
        return todoList.stream()
                .anyMatch(todo -> todo.getContent().equals(content));
    }

    public Todo save(String content) {
        // 이미 저장된 내용과 같은 내용은 입력 못함
        boolean isDuplicate = todoList.stream()
                .anyMatch(todo -> todo.getContent().equals(content));
        if (isDuplicate) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "이미 존재하는 할 일입니다");
        }

        var todo = new Todo(content);
        todoList.add(todo);

        return todo;
    }

    public void destroy(String id) {
        Todo todo = todoList.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 할 일입니다"));

        todoList.remove(todo);
    }

    // Spring이 bean을 생성하고 의존성 주입을 완료한 후에 딱 한번만 실행되는 어노테이션
    @PostConstruct
    private void init() {
        todoList.add(new Todo("Spring Boot 사용해보기"));
        todoList.add(new Todo("Htmx 사용해보기"));
        todoList.add(new Todo("JOOQ 사용해보기"));
        todoList.add(new Todo("젠레스존제로 일일퀘스트 해야함"));
        todoList.add(new Todo("풀업 10개씩 2세트"));
    }
}
