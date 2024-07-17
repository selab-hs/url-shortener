package com.urlshortener.shortener.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ShortCodeResponse {
    private String shortCode;

    public static ShortCodeResponse from(String shortCode){
        return new ShortCodeResponse(shortCode);
    }
}