package com.example.demo.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice(annotations = Controller.class)
public class WebExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public String handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes
    ) {
        // Valid 에러는 여러 목록이 한번에 보임.
        // 필요한 에러메시지 리스트 형태로 변경하여 redirect attribute에 넘겨주기
        // 플래시 attribute로 담아서 리다이랙트 할 경우, 페이지에서 모델값을 사용할 수 있음
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getField() + ": " + x.getDefaultMessage())
                .toList();
        redirectAttributes.addFlashAttribute("errors", errors);

        // 요청 헤더 정보엔 요청했던 경로 출처를 Referer에서 받을 수 있음.
        var referer = request.getHeader("Referer");
        return "redirect:" + (referer != null ? referer : "/");
    }

    @ExceptionHandler(value = ResponseStatusException.class)
    public String handleResponseStatus(
            ResponseStatusException ex,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes
    ) {
        // ResponseStatusException에 두번째 인자값으로 넘겼던 에러 사유를 errors 파라미터에 담기
        // 플래시 attribute로 담아서 리다이랙트 할 경우, 페이지에서 모델값을 사용할 수 있음
        List<String> errors = new ArrayList<>();
        errors.add(ex.getReason());
        redirectAttributes.addFlashAttribute("errors", errors);

        // 요청 헤더 정보엔 요청했던 경로 출처를 Referer에서 받을 수 있음.
        var referer = request.getHeader("Referer");
        return "redirect:" + (referer != null ? referer : "/");
    }
}
