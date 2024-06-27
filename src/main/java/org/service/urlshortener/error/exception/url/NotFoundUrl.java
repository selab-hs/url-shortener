package org.service.urlshortener.error.exception.url;

import org.service.urlshortener.error.dto.ErrorMessage;
import org.service.urlshortener.error.exception.BusinessException;

public class NotFoundUrl extends BusinessException {
    public NotFoundUrl(ErrorMessage message) {
        super(message);
    }
}