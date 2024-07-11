package com.urlshortener.member.domain.dto.response;

import com.urlshortener.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponseDto {
    private Long id;
    private String uuid;

    @Builder
    public MemberResponseDto(Long id, String uuid) {
        this.id = id;
        this.uuid = uuid;
    }

    public MemberResponseDto toDto(Member member) {
        return MemberResponseDto.builder()
                .id(member.getId())
                .uuid(member.getUuid())
                .build();
    }
}
