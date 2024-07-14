package com.urlshortener.shortener.dto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShortUrlModel {
    private Long id;
    private Long memberId;
    private String originalUrl;
    private LocalDateTime createAtl;

    public static ShortUrlModel from(Long id, Long memberId, String originalUrl, LocalDateTime createAtl) {
        return new ShortUrlModel(id, memberId, originalUrl, createAtl);
    }
}