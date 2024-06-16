package org.service.urlshortener.shortener.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShortUrlRequest {
    private String shortUrl;
}
