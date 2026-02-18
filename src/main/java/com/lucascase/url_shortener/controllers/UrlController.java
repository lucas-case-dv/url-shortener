package com.lucascase.url_shortener.controllers;

import com.lucascase.url_shortener.models.Url;
import com.lucascase.url_shortener.models.dto.UrlDTO;
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

    @GetMapping("/{shortCode}")
    public ResponseEntity<Url> findByShortCode(@PathVariable String shortCode) {
        return ResponseEntity.ok(this.urlService.findByShortCode(shortCode));
    }

    @PostMapping
    public ResponseEntity<Url> create(@Valid @RequestBody UrlDTO url) {
        Url newUrl = this.urlService.create(url);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{shortCode}").buildAndExpand(newUrl.getShortCode()).toUri();
        return ResponseEntity.created(uri).body(newUrl);
    }

    @PutMapping("/{shortCode}")
    public ResponseEntity<Url> update(@PathVariable String shortCode, @Valid @RequestBody UrlDTO newUrl) {
        Url updatedUrl = this.urlService.update(shortCode, newUrl);
        return ResponseEntity.ok(updatedUrl);
    }

    @DeleteMapping("/{shortCode}")
    public ResponseEntity<Void> delete(@PathVariable String shortCode) {
        this.urlService.delete(shortCode);
        return ResponseEntity.noContent().build();
    }

}
