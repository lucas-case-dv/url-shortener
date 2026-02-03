package com.lucascase.url_shortener.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = Url.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Url {

    public static final String TABLE_NAME = "urls";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "original", nullable = false, length = 1000)
    @Size(min = 3, max = 1000)
    @NotBlank
    private String originalUrl;

    @Column(name = "short_code", unique = true, nullable = false)
    private String shortCode;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt =  LocalDateTime.now();
}
