package com.urlshortener.shortener.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OriginUrlRequest {
    /**
     * 요청받은 원본 url
     */
    private String originUrl;
}