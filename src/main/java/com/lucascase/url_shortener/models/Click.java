package com.lucascase.url_shortener.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = Click.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Click {

    public static final String TABLE_NAME = "click";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "url_id")
    private Url url;

    @Column(name = "user_browser", length = 500, nullable = false)
    @Size(min = 2, max = 500)
    @NotBlank
    private String userBrowser;

    @Column(name = "user_os", length = 500, nullable = false)
    @Size(min = 2, max = 500)
    @NotBlank
    private String userOS;

    @Column(name = "referer", length = 100, nullable = false)
    @Size(min = 2, max = 100)
    @NotBlank
    private String referer;

    @Column(name = "createdAt", updatable = false)
    private String timestamp;
}
