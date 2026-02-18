package com.lucascase.url_shortener.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UrlDTO(
        @NotBlank(message = "URL cannot be empty")
        @Size(min = 3, message = "URL too short")
        String originalUrl
) {}
