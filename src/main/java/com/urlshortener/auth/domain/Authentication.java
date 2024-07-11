package com.urlshortener.auth.domain;

import com.urlshortener.member.domain.vo.MemberType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Authentication {
    private MemberDetail userDetail;
    private MemberType memberType;
}