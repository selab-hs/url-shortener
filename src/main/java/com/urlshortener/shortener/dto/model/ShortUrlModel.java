package com.urlshortener.shortener.dto.model;

import com.urlshortener.shortener.domain.ShortUrl;
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

    public static ShortUrlModel from(Long memberId, ShortUrl shortUrl) {
        return ShortUrlModel.builder()
                .id(shortUrl.getId())
                .originalUrl(shortUrl.getOriginUrl())
                .memberId(memberId)
                .createAtl(shortUrl.getCreatedAt())
                .build();
    }

    public static ShortUrlModel from(ShortUrl shortUrl) {
        return ShortUrlModel.builder()
                .id(shortUrl.getId())
                .originalUrl(shortUrl.getOriginUrl())
                .memberId(shortUrl.getMemberId())
                .createAtl(shortUrl.getCreatedAt())
                .build();
    }
}
