package com.urlshortener.actionlog.dto;

import com.urlshortener.actionlog.domain.SystemActionLog;
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
    
    public static SystemActionLogResponse from(SystemActionLog log) {
        return SystemActionLogResponse.builder()
                .ipAddress(log.getIpAddress())
                .method(log.getHttpMethod())
                .path(log.getPath())
                .userAgent(log.getUserAgent())
                .host(log.getHost())
                .referer(log.getReferer())
                .build();
    }
}
