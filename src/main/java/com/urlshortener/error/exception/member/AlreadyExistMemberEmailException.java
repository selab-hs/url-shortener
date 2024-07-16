package com.urlshortener.error.exception.member;

import com.urlshortener.error.dto.ErrorMessage;
import com.urlshortener.error.exception.BusinessException;

public class AlreadyExistMemberEmailException extends BusinessException {
    public AlreadyExistMemberEmailException(ErrorMessage message) {
        super(message);
    }
}