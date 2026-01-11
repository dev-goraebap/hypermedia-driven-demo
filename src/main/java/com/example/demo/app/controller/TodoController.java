package com.example.demo.app.controller;

import com.example.demo.app.domain.Todo;
import com.example.demo.app.domain.TodoService;
import com.example.demo.app.dto.TodoCreateRequest;
import com.example.demo.common.annotation.ErrorTemplate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public String index(Model model) {
        List<Todo> todoList = todoService.getTodoList();
        model.addAttribute("todoList", todoList);
        return "pages/todos/index";
    }

    @PostMapping
    @ErrorTemplate("pages/todos/_createFail")
    public ResponseEntity<Void> create(@Valid @ModelAttribute TodoCreateRequest dto) {
        todoService.save(dto.content());
        return ResponseEntity.ok()
                .header("HX-Location", "/todos")
                .build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> destroy(@PathVariable String id) {
        todoService.destroy(id);
        return ResponseEntity.ok()
                .header("HX-Location", "/todos")
                .build();
    }
}