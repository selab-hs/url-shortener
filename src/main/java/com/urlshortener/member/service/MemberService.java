package com.urlshortener.member.service;

import com.urlshortener.error.dto.ErrorMessage;
import com.urlshortener.error.exception.member.AlreadyExistMemberEmailException;
import com.urlshortener.error.exception.member.DuplicateMemberException;
import com.urlshortener.member.domain.Member;
import com.urlshortener.member.domain.dto.request.JoinMemberRequest;
import com.urlshortener.member.domain.dto.response.MemberResponseDto;
import com.urlshortener.member.domain.vo.Email;
import com.urlshortener.member.repository.MemberRepository;
import com.urlshortener.shortener.repository.ShortUrlRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.urlshortener.util.HttpUtil.getClientIdFromCookie;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final ShortUrlRepository shortUrlRepository;

    /**
     * 회원가입 기능을 수행하는 메서드
     *
     * @param request
     * @return
     */
    @Transactional
    public MemberResponseDto createMember(JoinMemberRequest request, HttpServletRequest httpRequest) {
        if (memberRepository.existsByEmail(new Email(request.getEmail()))) {
            throw new DuplicateMemberException(ErrorMessage.DUPLICATE_MEMBER_EXCEPTION);
        }
        var member = memberRepository.save(
                new Member(request.getEmail(),
                        request.getPassword()));
        shortUrlRepository.updateShortenerUrlMemberId(
                getClientIdFromCookie(httpRequest),
                member.getId());

        return MemberResponseDto.from(member.getId(), member.getEmail().getEmail());
    }

    @Transactional(readOnly = true)
    public void duplicateValidationMemberEmail(String email) {
        memberRepository.findByEmail(new Email(email))
                .ifPresent(member -> {
                    throw new AlreadyExistMemberEmailException(ErrorMessage.ALREADY_EXIST_MEMBER_EMAIL_EXCEPTION);
                });
    }
}