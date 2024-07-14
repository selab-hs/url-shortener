package com.urlshortener.actionlog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SystemActionLogResponse {
    private String method;
    private String path;
    private String userAgent;
    private String host;
    private String referer;

    @Builder
    public static SystemActionLogResponse from(String method, String path, String userAgent, String host, String referer) {
        return SystemActionLogResponse.builder()
                .method(method)
                .path(path)
                .userAgent(userAgent)
                .host(host)
                .referer(referer)
                .build();
    }
}
