package com.urlshortener.shortener.dto.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ShortUrlModel {
    private Long id;
    private String originalUrl;
    private LocalDateTime createAtl;

    public static ShortUrlModel from(Long id, String originalUrl, LocalDateTime createAtl){
        ShortUrlModel shortUrlModel = new ShortUrlModel();
        shortUrlModel.id = id;
        shortUrlModel.originalUrl = originalUrl;
        shortUrlModel.createAtl = createAtl;

        return shortUrlModel;
    }
}