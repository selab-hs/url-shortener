package org.service.urlshortener.shortener.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ShortCodeResponse {
    private String shortCode;
}