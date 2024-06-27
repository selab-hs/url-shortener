package org.service.urlshortener.shortener.repository;

import org.service.urlshortener.shortener.domain.OriginUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OriginUrlRepository extends JpaRepository<OriginUrl, Long> {
    List<OriginUrl> findByCreatedAtBefore(LocalDateTime date);
}