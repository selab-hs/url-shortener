package org.service.urlshortener.shortener.repository;

import org.service.urlshortener.shortener.domain.OriginUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface OriginUrlRepository extends JpaRepository<OriginUrl, Long> {
    List<OriginUrl> findByCreatedAtBefore(LocalDateTime date);

    Optional<OriginUrl> findByOriginUrl(String originUrl);

    Boolean existsByOriginUrl(String originUrl);
}