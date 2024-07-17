package com.urlshortener.member.domain.vo;

import com.urlshortener.error.dto.ErrorMessage;
import com.urlshortener.error.exception.member.InvalidPasswordException;
import lombok.Getter;

import java.util.regex.Pattern;

@Getter
public class Password {
    private static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$";
    private String password;

    public Password(String password) {
        validatePassword(password);
    }

    private void validatePassword(String password) {
        if (!Pattern.matches(PASSWORD_REGEX, password)) {
            throw new InvalidPasswordException(ErrorMessage.INVALID_PASSWORD_REGEX_EXCEPTION);
        }
        this.password = password;
    }

    public void setEncodePassword(String encodePassword) {
        this.password = encodePassword;
    }
}