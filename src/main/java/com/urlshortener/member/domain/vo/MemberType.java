package com.urlshortener.member.domain.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberType {
    GUEST("GUEST", "게스트"),
    USER("ROLE_USER", "유저"),
    ADMIN("ROLE_ADMIN", "관리자"),
    ;

    private final String name;
    private final String value;
}