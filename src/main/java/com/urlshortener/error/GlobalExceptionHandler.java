package com.urlshortener.error;

import lombok.extern.slf4j.Slf4j;
import com.urlshortener.error.dto.ErrorMessage;
import com.urlshortener.error.dto.ErrorResponseDto;
import com.urlshortener.error.exception.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponseDto> handleBusinessException(BusinessException e) {
        log.error("BusinessException", e.getCause());
        ErrorMessage errorMessage = e.getErrorMessage();
        return ErrorResponseDto.of(errorMessage);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponseDto> handleException(Exception e) {
        log.error("Exception", e.getCause());
        return ErrorResponseDto.of(ErrorMessage.INTERNAL_SERVER_ERROR);
    }
}