package com.urlshortener.error.exception.auth;

import com.urlshortener.error.dto.ErrorMessage;
import com.urlshortener.error.exception.BusinessException;

public class ReadysJwtException extends BusinessException {
    public ReadysJwtException(ErrorMessage message) {
        super(message);
    }
}