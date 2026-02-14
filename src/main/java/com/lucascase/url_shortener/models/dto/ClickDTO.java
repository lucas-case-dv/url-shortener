package com.lucascase.url_shortener.models.dto;

import java.time.LocalDateTime;

public record ClickDTO (
    LocalDateTime clickedAt,
    String userAgent,
    String referer
){}

