package com.urlshortener.error.exception.member;

import com.urlshortener.error.dto.ErrorMessage;
import com.urlshortener.error.exception.BusinessException;

public class DuplicateMemberException extends BusinessException {
    public DuplicateMemberException(ErrorMessage message) {
        super(message);
    }
}