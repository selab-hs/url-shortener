package com.urlshortener.member.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponseDto {
    private Long id;
    private String email;

    public static MemberResponseDto from(Long id, String email) {
        return new MemberResponseDto(id, email);
    }
}