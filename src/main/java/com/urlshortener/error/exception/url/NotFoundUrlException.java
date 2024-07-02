package com.urlshortener.error.exception.url;

import com.urlshortener.error.dto.ErrorMessage;
import com.urlshortener.error.exception.BusinessException;

public class NotFoundUrlException extends BusinessException {
    public NotFoundUrlException(ErrorMessage message) {
        super(message);
    }
}