package com.urlshortener.util;

import com.urlshortener.error.dto.ErrorMessage;
import com.urlshortener.error.exception.url.NotFoundClientIdHeaderException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;

public class HttpUtil {
    @Value("${security.separator}")
    public static String TOKEN_CODE;


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

    /**
     * 쿠키 조회
     *
     * @param request 쿠키 조회
     * @return clientCookie UUID
     */
    public static String getClientIdFromCookie(HttpServletRequest request) {
        if (request.getCookies() == null) {
            throw new NotFoundClientIdHeaderException(ErrorMessage.NOT_FOUND_CLIENT_ID_HEADER);
        }

        return Arrays.stream(request.getCookies())
                .filter(cookie -> "client-id".equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElseThrow(() -> new NotFoundClientIdHeaderException(ErrorMessage.NOT_FOUND_CLIENT_ID_HEADER));
    }
}