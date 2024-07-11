package com.urlshortener.error.exception.member;

import com.urlshortener.error.dto.ErrorMessage;
import com.urlshortener.error.exception.BusinessException;

public class NotExistUserInfoException extends BusinessException {
    public NotExistUserInfoException(ErrorMessage message) {
        super(message);
    }
}
