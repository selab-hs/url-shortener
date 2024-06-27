package org.service.urlshortener.error.dto;

import org.springframework.http.HttpStatus;

public enum ErrorMessage {


    /*
    * 서버 내부 오류
    **/
    INTERNAL_SERVER_ERROR(HttpStatus.BAD_REQUEST, "내부 서버 오류"),

    NOT_FOUND_URL(HttpStatus.NOT_FOUND, "존재하지 않는 url"),
    RATE_LIMIT_EXCEEDED(HttpStatus.BAD_REQUEST, "요청 횟수 초과"),
    ;
    private final HttpStatus status;
    private final String message;

    ErrorMessage(HttpStatus status, String message){
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus(){
        return status;
    }

    public String getMessage(){
        return message;
    }
}