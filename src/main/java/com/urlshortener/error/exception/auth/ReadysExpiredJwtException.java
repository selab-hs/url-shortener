package com.urlshortener.error.exception.auth;

import com.urlshortener.error.dto.ErrorMessage;
import com.urlshortener.error.exception.BusinessException;

public class ReadysExpiredJwtException extends BusinessException {
    public ReadysExpiredJwtException(ErrorMessage message) {
        super(message);
    }
}
