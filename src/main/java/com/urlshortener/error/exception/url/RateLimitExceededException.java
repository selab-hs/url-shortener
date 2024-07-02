package com.urlshortener.error.exception.url;

import com.urlshortener.error.dto.ErrorMessage;
import com.urlshortener.error.exception.BusinessException;

public class RateLimitExceededException extends BusinessException {

    public RateLimitExceededException(ErrorMessage message) {
        super(message);
    }
}
