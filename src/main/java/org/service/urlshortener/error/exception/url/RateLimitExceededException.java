package org.service.urlshortener.error.exception.url;

import org.service.urlshortener.error.dto.ErrorMessage;
import org.service.urlshortener.error.exception.BusinessException;

public class RateLimitExceededException extends BusinessException {

    public RateLimitExceededException(ErrorMessage message) {
        super(message);
    }
}
