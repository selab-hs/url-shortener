package com.urlshortener.shortener.repository;

public interface ShortUrlCustomRepository {
    void updateShortenerUrlMemberId(String uuid, Long memberId);
}