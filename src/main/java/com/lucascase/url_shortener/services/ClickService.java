package com.lucascase.url_shortener.services;

import com.lucascase.url_shortener.models.Click;
import com.lucascase.url_shortener.models.Url;
import com.lucascase.url_shortener.models.dto.ClickDTO;
import com.lucascase.url_shortener.repositories.ClickRepository;
import com.lucascase.url_shortener.repositories.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClickService {

    @Autowired
    private ClickRepository clickRepository;

    @Autowired
    private UrlRepository urlRepository;

    @Async
    @Transactional
    public void createClick (Url url, String userAgent, String referer) {
        Click click = new Click();
        click.setUrl(url);
        click.setUserAgent((userAgent != null && !userAgent.isBlank()) ? userAgent : "Unknown");
        click.setReferer((referer != null && !referer.isBlank()) ? referer : "Direct");
        click.setTimestamp(LocalDateTime.now());

        urlRepository.incrementClicksCount(url.getId());
        this.clickRepository.save(click);
    }

    public List<ClickDTO> getStats(String code) {
        List<Click> clicks = clickRepository.findByUrl_ShortCode(code);

        return clicks.stream()
                .map(click -> new ClickDTO(
                        click.getTimestamp(),
                        click.getUserAgent(),
                        click.getReferer()
                ))
                .toList();
    }
}
