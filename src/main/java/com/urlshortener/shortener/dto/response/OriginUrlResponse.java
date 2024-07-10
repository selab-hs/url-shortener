package com.urlshortener.shortener.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OriginUrlResponse {
    private String originUrl;

    public static OriginUrlResponse from(String originUrl){
        return new OriginUrlResponse(originUrl);
    }
}