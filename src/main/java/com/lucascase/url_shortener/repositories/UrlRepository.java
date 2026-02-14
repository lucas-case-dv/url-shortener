package com.lucascase.url_shortener.repositories;

import com.lucascase.url_shortener.models.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

    @Transactional(readOnly = true)
    Optional<Url> findByShortCode(String shortCode);

    @Modifying
    @Transactional
    @Query("UPDATE Url u SET u.clicks = u.clicks + 1 WHERE u.id = :id")
    void incrementClicksCount(@Param("id") Long id);
}
