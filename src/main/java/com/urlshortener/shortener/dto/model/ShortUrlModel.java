package com.urlshortener.shortener.dto.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ShortUrlModel {
    private Long id;
    private Long memberId;
    private String originalUrl;
    private LocalDateTime createAtl;

    public static ShortUrlModel from(Long id, Long memberId, String originalUrl, LocalDateTime createAtl) {
        ShortUrlModel shortUrlModel = new ShortUrlModel();
        shortUrlModel.memberId = memberId;
        shortUrlModel.id = id;
        shortUrlModel.originalUrl = originalUrl;
        shortUrlModel.createAtl = createAtl;

        return shortUrlModel;
    }
}