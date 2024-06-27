package org.service.urlshortener.error.exception.url;

import org.service.urlshortener.error.dto.ErrorMessage;
import org.service.urlshortener.error.exception.BusinessException;

public class RateLimitExceeded extends BusinessException {

    public RateLimitExceeded(ErrorMessage message) {
        super(message);
    }
}
