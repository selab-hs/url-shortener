package com.urlshortener.util;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;

/**
 * todo : 사용자 ID,UUID 호출하는 로직 추가 예정
 */
public class HttpUtil {
    public static String getClientIpAddress(HttpServletRequest request) {
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

//    public static String getToksUserKeyUUID(HttpServletRequest request) {
//        return request.getHeader(Constants.USER_UUID);
//    }
}

