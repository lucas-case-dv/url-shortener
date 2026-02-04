package com.lucascase.url_shortener.controllers;

import com.lucascase.url_shortener.models.Url;
import com.lucascase.url_shortener.services.UrlService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/urls")
@Validated
public class UrlController {

    @Autowired
    private UrlService urlService;

    @GetMapping("/{id}")
    public ResponseEntity<Url> findById(@PathVariable Long id) {
        return ResponseEntity.ok(this.urlService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Url> create(@Valid @RequestBody Url url) {
        Url newUrl = this.urlService.create(url);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newUrl.getId()).toUri();
        return ResponseEntity.created(uri).body(newUrl);
    }

}
