package com.urlshortener.error.exception.health;


import com.urlshortener.error.dto.ErrorMessage;
import com.urlshortener.error.exception.BusinessException;

public class ExampleException extends BusinessException {
    public ExampleException(ErrorMessage message) {
        super(message);
    }
}