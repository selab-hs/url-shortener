package com.urlshortener.shortener.repository;

import com.urlshortener.shortener.domain.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Repository
public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long>, ShortUrlCustomRepository {
    List<ShortUrl> findAllByIdIn(List<Long> ids);

    List<ShortUrl> findByMemberId(Long memberId);
}