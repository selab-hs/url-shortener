package com.urlshortener.auth.domain;

import com.urlshortener.member.domain.Member;
import com.urlshortener.member.domain.vo.MemberType;
import lombok.Getter;

@Getter
public class MemberDetail {
    private Long id;
    private String userEmail;
    private MemberType memberType;

    public MemberDetail(Member member) {
        this.id = member.getId();
        this.userEmail = member.getEmail().getEmail();
        this.memberType = MemberType.USER;
    }

    public MemberDetail() {
        this.memberType = MemberType.GUEST;
    }
}