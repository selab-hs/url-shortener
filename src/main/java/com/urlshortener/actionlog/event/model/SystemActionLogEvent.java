package com.urlshortener.actionlog.event.model;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;

import static com.urlshortener.util.HttpUtil.getClientIpAddress;

@Getter
public class SystemActionLogEvent {
    private final String ipAddress;
    private final String method;
    private final String path;
    private final String userAgent;
    private final String host;
    private final String referer;

    public SystemActionLogEvent(HttpServletRequest request) {
        this.ipAddress = getClientIpAddress(request);
        this.method = request.getMethod();
        this.path = request.getRequestURI();
        this.userAgent = request.getHeader("User-Agent");
        this.host = request.getHeader("Host");
        this.referer = request.getHeader("Referer");
    }
}
