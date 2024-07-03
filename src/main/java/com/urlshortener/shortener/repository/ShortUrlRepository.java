package com.urlshortener.shortener.repository;

import com.urlshortener.shortener.domain.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {
}