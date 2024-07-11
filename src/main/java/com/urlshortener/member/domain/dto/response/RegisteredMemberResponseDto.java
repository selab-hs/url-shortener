package com.urlshortener.member.domain.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisteredMemberResponseDto {
    private Long id;
    private Long memberId;
    private String email;

    @Builder
    public RegisteredMemberResponseDto(Long id, Long memberId, String email) {
        this.id = id;
        this.memberId = memberId;
        this.email = email;
    }
}
