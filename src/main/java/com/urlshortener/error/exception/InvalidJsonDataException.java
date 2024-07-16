package com.urlshortener.error.exception;

import com.urlshortener.error.dto.ErrorMessage;

public class InvalidJsonDataException extends BusinessException {
    public InvalidJsonDataException(ErrorMessage message) {
        super(message);
    }
}