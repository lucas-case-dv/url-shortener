package com.lucascase.url_shortener.repositories;

import com.lucascase.url_shortener.models.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

    @Transactional(readOnly = true)
    Optional<Url> findByShortCode(String shortCode);

}
