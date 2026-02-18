package com.lucascase.url_shortener.repositories;

import com.lucascase.url_shortener.models.Click;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ClickRepository extends JpaRepository<Click, Long> {

    List<Click> findByUrl_ShortCode(String urlCode);

    @Modifying
    @Transactional
    void deleteByUrl_ShortCode(String urlCode);
}
