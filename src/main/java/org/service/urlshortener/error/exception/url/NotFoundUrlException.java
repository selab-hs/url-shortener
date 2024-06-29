package org.service.urlshortener.error.exception.url;

import org.service.urlshortener.error.dto.ErrorMessage;
import org.service.urlshortener.error.exception.BusinessException;

public class NotFoundUrlException extends BusinessException {
    public NotFoundUrlException(ErrorMessage message) {
        super(message);
    }
}