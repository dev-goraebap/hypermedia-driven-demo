package com.example.demo.app.controller;

import com.example.demo.app.domain.Todo;
import com.example.demo.app.domain.TodoService;
import com.example.demo.app.dto.TodoCreateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

    @GetMapping("/register")
    public String register() {
        return "pages/todos/register";
    }

    @PostMapping("/register")
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
            return "pages/todos/register";
        }

        try {
            todoService.save(dto.content());
        } catch (ResponseStatusException ex) {
            model.addAttribute("errors", List.of(ex.getMessage()));
            return "pages/todos/register";
        }

        return "redirect:/todos";
    }
}
