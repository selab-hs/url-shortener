package org.service.urlshortener.error.exception;

import org.service.urlshortener.error.dto.ErrorMessage;

public class InvalidJsonDataException extends BusinessException {
    public InvalidJsonDataException(ErrorMessage message) {
        super(message);
    }
}
