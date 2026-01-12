package com.example.demo.app.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record TodoCheckDuplicateRequest(
        @NotBlank
        @Length(min = 2, max = 30)
        String content
) {
}