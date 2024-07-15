package com.urlshortener.actionlog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SystemActionLogResponse {
    private String ipAddress;
    private String method;
    private String path;
    private String userAgent;
    private String host;
    private String referer;

    public static SystemActionLogResponse from(
            String ipAddress,
            String method,
            String path,
            String userAgent,
            String host,
            String referer
    ) {

        return SystemActionLogResponse.builder()
                .ipAddress(ipAddress)
                .method(method)
                .path(path)
                .userAgent(userAgent)
                .host(host)
                .referer(referer)
                .build();
    }
}
