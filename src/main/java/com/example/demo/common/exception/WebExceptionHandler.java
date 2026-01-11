package com.example.demo.common.exception;

import com.example.demo.common.annotation.ErrorTemplate;
import gg.jte.TemplateEngine;
import gg.jte.output.StringOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice(annotations = Controller.class)
@RequiredArgsConstructor
public class WebExceptionHandler {

    private static final String DEFAULT_TEMPLATE = "partials/defaultErrorModal";

    private final TemplateEngine templateEngine;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(
            MethodArgumentNotValidException ex,
            HandlerMethod handlerMethod
    ) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(x -> x.getField() + ": " + x.getDefaultMessage())
                .toList();

        return renderErrorResponse(errors, ex.getStatusCode(), handlerMethod);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatusException(
            ResponseStatusException ex,
            HandlerMethod handlerMethod
    ) {
        List<String> errors = List.of(Objects.requireNonNull(ex.getReason()));

        return renderErrorResponse(errors, ex.getStatusCode(), handlerMethod);
    }

    private ResponseEntity<String> renderErrorResponse(
            List<String> errors,
            HttpStatusCode statusCode,
            HandlerMethod handlerMethod
    ) {
        ErrorTemplate annotation = handlerMethod.getMethodAnnotation(ErrorTemplate.class);
        String templatePath = (annotation != null ? annotation.value() : DEFAULT_TEMPLATE) + ".jte";

        StringOutput output = new StringOutput();
        templateEngine.render(templatePath, Map.of("errors", errors), output);

        return ResponseEntity
                .status(statusCode)
                .contentType(MediaType.TEXT_HTML)
                .body(output.toString());
    }
}
