package com.lucascase.url_shortener.services;

import com.lucascase.url_shortener.models.Url;
import com.lucascase.url_shortener.repositories.UrlRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    public Url findById(Long id) {
        Url url = this.urlRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("URL not found."));
        return url;
    }
}
