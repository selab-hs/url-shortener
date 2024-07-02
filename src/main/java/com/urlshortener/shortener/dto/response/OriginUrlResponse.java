package com.urlshortener.shortener.dto.response;

import lombok.Getter;

@Getter
public class OriginUrlResponse {
    private String originUrl;

    public static OriginUrlResponse from(String originUrl){
        OriginUrlResponse originUrlResponse = new OriginUrlResponse();
        originUrlResponse.originUrl = originUrl;

        return originUrlResponse;
    }
}