package com.urlshortener.error.exception;

import com.urlshortener.error.dto.ErrorMessage;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final ErrorMessage errorMessage;
    private final String reason;

    public BusinessException(ErrorMessage message) {
        super(message.getMessage());
        this.errorMessage = message;
        this.reason = message.getMessage();
    }

    public BusinessException(ErrorMessage message, String reason) {
        super(message.getMessage());
        this.errorMessage = message;
        this.reason = reason;
    }
}
