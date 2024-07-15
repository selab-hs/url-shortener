package com.urlshortener.member.service;

import com.urlshortener.error.dto.ErrorMessage;
import com.urlshortener.error.exception.member.DuplicateMemberException;
import com.urlshortener.member.domain.Member;
import com.urlshortener.member.domain.dto.request.JoinMemberRequest;
import com.urlshortener.member.domain.dto.response.MemberResponseDto;
import com.urlshortener.member.domain.vo.Email;
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

    /**
     * 회원가입 기능을 수행하는 메서드
     *
     * @param request
     * @return
     */
    @Transactional
    public MemberResponseDto createMember(JoinMemberRequest request) {
        if (memberRepository.existsByEmail(new Email(request.getEmail()))) {
            throw new DuplicateMemberException(ErrorMessage.DUPLICATE_MEMBER_EXCEPTION);
        }
        var member = memberRepository.save(
                new Member(request.getEmail(),
                        request.getPassword()));

        return MemberResponseDto.from(member.getId(), member.getEmail().getEmail());
    }
}
