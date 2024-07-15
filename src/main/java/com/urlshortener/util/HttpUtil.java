package com.urlshortener.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;

public class HttpUtil {
    @Value("${security.separator}")
    public static String TOKEN_CODE;

    public static String getClientIpAddress(HttpServletRequest request) {
        if (request.getHeader("X-READYS-AUTH-TOKEN") == null) {

            return noneMember(request);
        }

        return TOKEN_CODE + (request.getHeader("X-READYS-AUTH-TOKEN"));
    }

    private static String noneMember(HttpServletRequest request) {
        var headers = new String[]{
                "X-Forwarded-For",
                "Proxy-Client-IP",
                "WL-Proxy-Client-IP",
                "HTTP_CLIENT_IP",
                "HTTP_X_FORWARDED_FOR"
        };

        return Arrays.stream(headers)
                .map(request::getHeader)
                .filter(ip -> ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip))
                .findFirst()
                .orElse(request.getRemoteAddr());
    }
}

