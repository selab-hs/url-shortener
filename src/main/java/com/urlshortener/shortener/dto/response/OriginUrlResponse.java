package com.urlshortener.shortener.dto.response;

import com.urlshortener.shortener.dto.model.ShortUrlModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OriginUrlResponse {
    private String originUrl;

    public static OriginUrlResponse from(ShortUrlModel shortUrl) {
        return new OriginUrlResponse(shortUrl.getOriginalUrl());
    }
}