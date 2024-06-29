package org.service.urlshortener.error.exception.url;

import org.service.urlshortener.error.dto.ErrorMessage;
import org.service.urlshortener.error.exception.BusinessException;

public class NotFinishRemoveSixMonthsOldDataException extends BusinessException {
    public NotFinishRemoveSixMonthsOldDataException(ErrorMessage message) {
        super(message);
    }
}
