package com.urlshortener.actionlog.even;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;

@Getter
public class SystemActionLogEvent {
    //private final String ipAddress;
    private final String method;
    private final String path;
    private final String userAgent;
    private final String host;
    private final String referer;

    public SystemActionLogEvent(HttpServletRequest request) {
        // this.ipAddress = null;
        this.method = request.getMethod();
        this.path = request.getRequestURI();
        this.userAgent = request.getHeader("User-Agent");
        this.host = request.getHeader("Host");
        this.referer = request.getHeader("Referer");
    }
}
