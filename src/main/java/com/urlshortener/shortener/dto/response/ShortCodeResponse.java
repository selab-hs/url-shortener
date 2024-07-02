package com.urlshortener.shortener.dto.response;

import lombok.Getter;

@Getter
public class ShortCodeResponse {
    private String shortCode;

    public static ShortCodeResponse from(String shortCode){
        ShortCodeResponse shortCodeResponse = new ShortCodeResponse();
        shortCodeResponse.shortCode = shortCode;

        return shortCodeResponse;
    }
}