package com.lucascase.url_shortener.services;

import com.lucascase.url_shortener.models.Url;
import com.lucascase.url_shortener.repositories.UrlRepository;
import com.lucascase.url_shortener.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    @Autowired
    private ClickService clickService;

    public Url findById(Long id) {
        Url url = this.urlRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("URL not found."));
        return url;
    }

    public Url findByShortCode(String shortCode) {
        Url url = this.urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new ObjectNotFoundException("URL not found."));
        return url;
    }

    //Encodes ID using Base62
    public String encode(Long id) {
        String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();

        while (id > 0) {
            sb.append(characters.charAt((int) (id % 62)));
            id /= 62;
        }

        return sb.reverse().toString();
    }

    @Transactional
    public Url create(Url obj) {
        obj.setId(null);
        obj.setShortCode("temp");
        obj.setClicks(0);
        Url url = this.urlRepository.save(obj);

        String shortCode = encode(url.getId());
        url.setShortCode(shortCode);

        return this.urlRepository.save(url);
    }
}
