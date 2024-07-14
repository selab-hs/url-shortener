package com.urlshortener.error.exception.member;

import com.urlshortener.error.dto.ErrorMessage;
import com.urlshortener.error.exception.BusinessException;

public class InvalidPasswordMatchException extends BusinessException {
    public InvalidPasswordMatchException(ErrorMessage message) {
        super(message);
    }
}
