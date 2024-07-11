package com.urlshortener.member.service;

import com.urlshortener.member.domain.Member;
import com.urlshortener.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Long getOrCreateMember(String uuid) {
        var member = memberRepository.findByUuid(uuid)
                .orElse(createGuestMember(uuid));

        return member.getId(); // DTO로 변경 예정
    }

    public Member createGuestMember(String uuid) {
        var createMember = Member.builder()
                .uuid(uuid)
                .build();
        memberRepository.save(createMember);

        return createMember;
    }

}
