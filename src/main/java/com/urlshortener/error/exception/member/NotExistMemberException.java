package com.urlshortener.error.exception.member;

import com.urlshortener.error.dto.ErrorMessage;
import com.urlshortener.error.exception.BusinessException;

public class NotExistMemberException extends BusinessException {
    public NotExistMemberException(ErrorMessage message) {
        super(message);
    }
}
