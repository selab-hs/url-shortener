package com.urlshortener.shortener.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ShortCodeAndSystemActionLogResponse {
    private String shortcode;
    private Long view;

    public static ShortCodeAndSystemActionLogResponse from(String shortcode, Long view) {
        return new ShortCodeAndSystemActionLogResponse(shortcode, view);
    }
}