package org.service.urlshortener.error.exception.url;

import org.service.urlshortener.error.dto.ErrorMessage;
import org.service.urlshortener.error.exception.BusinessException;

public class NotFinishRemoveSixMonthsOldData extends BusinessException {
    public NotFinishRemoveSixMonthsOldData(ErrorMessage message) {
        super(message);
    }
}
