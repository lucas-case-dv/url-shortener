package com.lucascase.url_shortener.controllers;

import com.lucascase.url_shortener.models.Url;
import com.lucascase.url_shortener.models.dto.ClickDTO;
import com.lucascase.url_shortener.services.ClickService;
import com.lucascase.url_shortener.services.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/")
@Validated
public class RedirectController {

    @Autowired
    private UrlService urlService;

    @Autowired
    private ClickService clickService;

    @GetMapping("{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode, HttpServletRequest request) {
        Url url = urlService.findByShortCode(shortCode);
        String userAgent = request.getHeader("User-Agent");
        String referer =  request.getHeader("Referer");
        clickService.createClick(url, userAgent, referer);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url.getOriginalUrl()))
                .build();
    }

    @GetMapping("{shortCode}/stats")
    public ResponseEntity<List<ClickDTO>> getStats(@PathVariable String shortCode) {
        List<ClickDTO> stats = clickService.getStats(shortCode);

        if (stats.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(stats);
    }
}
