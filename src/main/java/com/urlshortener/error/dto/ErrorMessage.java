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

    NOT_FOUND_CLIENT_ID_HEADER(HttpStatus.BAD_REQUEST, "요청 클라이언트 ID 헤더 없음"),

    EXPIRED_JWT_EXCEPTION(HttpStatus.BAD_REQUEST, "토큰이 만료되었습니다"),

    WRONG_JWT_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 토큰 정보입니다"),

    INVALID_EMAIL_REGEX_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 이메일 형식입니다"),

    INVALID_PASSWORD_REGEX_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 비밀번호 형식입니다"),

    INVALID_PASSWORD_MATCH_EXCEPTION(HttpStatus.BAD_REQUEST, "비밀번호가 맞지 않습니다"),

    NOT_EXIST_USER_INFO_EXCEPTION(HttpStatus.NOT_FOUND, "유저 정보가 존재하지 않습니다"),

    NOT_EXIST_MEMBER_EXCEPTION(HttpStatus.NOT_FOUND, "해당 유저가 존재하지 않습니다"),

    ALREADY_EXIST_MEMBER_EMAIL_EXCEPTION(HttpStatus.BAD_REQUEST, "이미 해당 이메일 정보가 등록되어 있습니다."),

    INVALID_LOGIN_USER_INFORMATION_EXCEPTION(HttpStatus.NOT_FOUND, "잘못된 로그인 정보입니다"),

    NOT_LOGIN_USER_EXCEPTION(HttpStatus.BAD_REQUEST, "로그인 정보가 없습니다"),

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