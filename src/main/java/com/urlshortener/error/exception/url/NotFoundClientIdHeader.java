package com.urlshortener.error.exception.url;

import com.urlshortener.error.dto.ErrorMessage;
import com.urlshortener.error.exception.BusinessException;

public class NotFoundClientIdHeader extends BusinessException {

    public NotFoundClientIdHeader(ErrorMessage message) {
        super(message);
    }
}
