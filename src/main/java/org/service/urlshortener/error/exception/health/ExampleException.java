package org.service.urlshortener.error.exception.health;


import org.service.urlshortener.error.dto.ErrorMessage;
import org.service.urlshortener.error.exception.BusinessException;

public class ExampleException extends BusinessException {
    public ExampleException(ErrorMessage message) {
        super(message);
    }
}
