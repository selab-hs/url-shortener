package com.urlshortener.shortener.repository;

import com.urlshortener.shortener.domain.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {
    List<ShortUrl> findByCreatedAtBefore(LocalDateTime date);
}