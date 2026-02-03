package com.lucascase.url_shortener.repositories;

import com.lucascase.url_shortener.models.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

    @Transactional(readOnly = true)
    Url findByShortCode(String shortCode);

}
