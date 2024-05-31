package org.service.urlshortener.error.exception;


import lombok.Getter;
import org.service.urlshortener.error.dto.ErrorMessage;

@Getter
public class BusinessException extends RuntimeException{

    private final ErrorMessage errorMessage;

    public BusinessException(ErrorMessage message){
        super(message.getMessage());
        this.errorMessage = message;
    }
}