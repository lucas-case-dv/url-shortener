package com.lucascase.url_shortener.models.dto;

import java.time.LocalDateTime;

public record ClickDTO (
    String clickedAt,
    String userBrowser,
    String userOS,
    String referer
){}

