package com.urlshortener.member.domain.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JoinRegisteredMemberRequestDto {
    private String email;
    private String password;
}
