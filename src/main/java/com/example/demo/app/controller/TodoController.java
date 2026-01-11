package com.example.demo.app.controller;

import com.example.demo.app.domain.Todo;
import com.example.demo.app.domain.TodoService;
import com.example.demo.app.dto.TodoCreateRequest;
import gg.jte.TemplateEngine;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;
    private final TemplateEngine templateEngine;

    @GetMapping
    public String index(Model model) {
        List<Todo> todoList = todoService.getTodoList();
        model.addAttribute("todoList", todoList);
        return "pages/todos/index";
    }

    @PostMapping
    public String create(
            @Valid @ModelAttribute TodoCreateRequest dto,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors()
                    .stream().map(x -> x.getField() + ": " + x.getDefaultMessage())
                    .toList();
            model.addAttribute("errors", errors);
            return "pages/todos/_createFail";
        }

        try {
            Todo todo = todoService.save(dto.content());
            model.addAttribute("todo", todo);
        } catch (ResponseStatusException ex) {
            model.addAttribute("errors", List.of(ex.getMessage()));
            return "pages/todos/_createFail";
        }

        return "pages/todos/_createSuccess";
    }

    @DeleteMapping("{id}")
    public String destroy(
            @PathVariable String id,
            Model model,
            HttpServletResponse response
    ) {
        try {
            todoService.destroy(id);
        } catch (ResponseStatusException ex) {
            response.setStatus(ex.getStatusCode().value());
            model.addAttribute("errors", List.of(Objects.requireNonNull(ex.getReason())));
            return "pages/todos/_destroyFail";
        }

        return null;
    }
}
