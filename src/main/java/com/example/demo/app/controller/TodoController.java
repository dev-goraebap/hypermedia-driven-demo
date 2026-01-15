package com.example.demo.app.controller;

import com.example.demo.app.domain.Todo;
import com.example.demo.app.domain.TodoService;
import com.example.demo.app.dto.TodoCheckDuplicateRequest;
import com.example.demo.app.dto.TodoFormRequest;
import com.example.demo.common.annotation.ErrorTemplate;
import jakarta.servlet.http.HttpServletResponse;
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

    @GetMapping("check-duplicate")
    @ErrorTemplate("pages/todos/_duplicateCheckBox")
    public String checkDuplicate(
            @Valid @ModelAttribute TodoCheckDuplicateRequest todoCheckDuplicateRequest,
            Model model
    ) throws InterruptedException {
        Thread.sleep(1000L);
        boolean result = todoService.checkDuplicate(todoCheckDuplicateRequest.content());
        model.addAttribute("isDuplicated", result);
        return "pages/todos/_duplicateCheckBox";
    }

    @PostMapping
    @ErrorTemplate("pages/todos/_createFail")
    public ResponseEntity<Void> create(@Valid @ModelAttribute TodoFormRequest dto) throws InterruptedException {
        Thread.sleep(3000L);

        todoService.save(dto.content());
        return ResponseEntity.ok()
                .header("HX-Location", "/todos")
                .build();
    }

    @GetMapping("{id}/edit")
    public String edit(
            @PathVariable String id,
            HttpServletResponse response,
            Model model
    ) throws InterruptedException {
        Thread.sleep(3000L);
        Todo todo = todoService.getTodo(id);
        model.addAttribute("todo", todo);
        return "pages/todos/edit";
    }

    @PutMapping("{id}")
    @ErrorTemplate("pages/todos/_editFail")
    public ResponseEntity<Void> update(
            @PathVariable String id,
            @Valid @ModelAttribute TodoFormRequest dto
    ) throws InterruptedException {
        Thread.sleep(1000L);

        todoService.update(id, dto.content());
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