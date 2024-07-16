package com.urlshortener.member.domain.vo;

import com.urlshortener.error.dto.ErrorMessage;
import com.urlshortener.error.exception.member.InvalidEmailException;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

@Getter
@NoArgsConstructor
@Embeddable
public class Email {
    private static final String EMAIL_REGEX = "[0-9a-zA-Z]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
    private String email;

    public Email(String email) {
        validateEmail(email);
    }

    private void validateEmail(String email) {
        if (!Pattern.matches(EMAIL_REGEX, email)) {
            throw new InvalidEmailException(ErrorMessage.INVALID_EMAIL_REGEX_EXCEPTION);
        }
        this.email = email;
    }
}