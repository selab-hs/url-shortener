package com.urlshortener.error.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorMessage {

    /**
     * 서버 내부 오류
     */
    INTERNAL_SERVER_ERROR(HttpStatus.BAD_REQUEST, "내부 서버 오류"),

    NOT_FOUND_URL(HttpStatus.NOT_FOUND, "존재하지 않는 url"),

    RATE_LIMIT_EXCEEDED(HttpStatus.BAD_REQUEST, "요청 횟수 초과"),

    NOT_FINISH_DELETE_SIX_MONTHS_OLD_DATA(HttpStatus.NO_CONTENT, "6개월이 지난 데이터 삭제 실패"),

    INVALID_JSON_DATA_ERROR(HttpStatus.BAD_REQUEST, "json data error"),
    ;
    private final HttpStatus status;
    private final String message;

    ErrorMessage(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

}