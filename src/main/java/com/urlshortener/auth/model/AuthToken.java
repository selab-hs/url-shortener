package com.urlshortener.auth.model;

import lombok.Data;

import static com.urlshortener.util.HeaderUtil.READYS_AUTH_HEADER_KEY;

@Data
public class AuthToken {
    private final String key;
    private final String token;

    public AuthToken(String token) {
        this.key = READYS_AUTH_HEADER_KEY;
        this.token = token;
    }
}
