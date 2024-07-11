package com.urlshortener.auth.domain;

import com.urlshortener.member.domain.RegisteredMember;
import com.urlshortener.member.domain.vo.MemberType;
import lombok.Getter;

@Getter
public class MemberDetail {
    private Long id;
    private Long memberId;
    private String userEmail;
    private MemberType memberType;

    public MemberDetail(RegisteredMember member) {
        this.id = member.getId();
        this.memberId = member.getMemberId();
        this.userEmail = member.getEmail().getEmail();
        this.memberType = MemberType.USER;
    }

    public MemberDetail() {
        this.memberType = MemberType.GUEST;
    }
}