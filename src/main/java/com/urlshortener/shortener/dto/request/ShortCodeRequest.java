package com.urlshortener.shortener.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShortCodeRequest {
    /**
     * 요청 받은 최적화 code
     */
    private String shortCode;
}
