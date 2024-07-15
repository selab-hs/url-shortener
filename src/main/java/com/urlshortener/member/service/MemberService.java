package com.urlshortener.member.service;

import com.urlshortener.member.domain.Member;
import com.urlshortener.member.domain.dto.request.JoinMemberRequest;
import com.urlshortener.member.domain.dto.response.MemberResponseDto;
import com.urlshortener.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponseDto createMember(JoinMemberRequest request) {
        var member = memberRepository.save(
                new Member(request.getEmail(),
                        request.getPassword()));

        return MemberResponseDto.from(member.getId(), member.getEmail().getEmail());
    }
}
