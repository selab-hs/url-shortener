package org.service.urlshortener.shortener.repository;

import org.service.urlshortener.shortener.domain.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShortenerRepository extends JpaRepository<Url, Long> {
    Optional<Url> findByLongUrl(String longUrl);
    Optional<Url> findByShortUrl(String shortUrl);
}
