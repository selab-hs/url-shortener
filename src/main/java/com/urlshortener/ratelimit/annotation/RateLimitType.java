package com.urlshortener.ratelimit.annotation;

import lombok.Getter;

@Getter
public enum RateLimitType {
    CREATE_SHORT_URL("short url 생성은 분당 10개까지 가능합니다"),
    ;

    private final String message;

    RateLimitType(String message) {
        this.message = message;
    }
}
