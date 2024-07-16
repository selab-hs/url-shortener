package com.urlshortener.error.exception.url;

import com.urlshortener.error.dto.ErrorMessage;
import com.urlshortener.error.exception.BusinessException;

public class NotFoundClientIdHeaderException extends BusinessException {
    public NotFoundClientIdHeaderException(ErrorMessage message) {
        super(message);
    }
}