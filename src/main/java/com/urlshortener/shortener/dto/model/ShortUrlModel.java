package com.urlshortener.shortener.dto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShortUrlModel {
    private Long id;
    private Long memberId;
    private String originalUrl;
    private LocalDateTime createAtl;

    public static ShortUrlModel from(Long id, String originalUrl, LocalDateTime createAtl){

        return ShortUrlModel.builder()
                .id(id)
                .originalUrl(originalUrl)
                .createAtl(createAtl)
                .build();
    }
}