package com.urlshortener.error.exception.url;

import com.urlshortener.error.dto.ErrorMessage;
import com.urlshortener.error.exception.BusinessException;

public class NotFinishRemoveSixMonthsOldDataException extends BusinessException {
    public NotFinishRemoveSixMonthsOldDataException(ErrorMessage message) {
        super(message);
    }
}