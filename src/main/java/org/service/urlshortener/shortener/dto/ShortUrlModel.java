package org.service.urlshortener.shortener.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ShortUrlModel {
    private Long id;
    private String originalUrl;
    private LocalDateTime createAtl;
}
