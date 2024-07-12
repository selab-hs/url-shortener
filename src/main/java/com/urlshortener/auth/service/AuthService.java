package com.urlshortener.auth.service;

import com.urlshortener.auth.domain.MemberDetail;
import com.urlshortener.auth.dto.JoinRequest;
import com.urlshortener.auth.token.TokenProvider;
import com.urlshortener.error.dto.ErrorMessage;
import com.urlshortener.error.exception.member.InvalidPasswordMatchException;
import com.urlshortener.error.exception.member.NotExistMemberException;
import com.urlshortener.error.exception.member.NotExistUserInfoException;
import com.urlshortener.member.domain.vo.Email;
import com.urlshortener.member.domain.vo.MemberType;
import com.urlshortener.member.repository.RegisterMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final TokenProvider tokenProvider;
    private final RegisterMemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * @param * id : 파싱한 token Long userID
     * @return * userId 조회를 통한 UserDetail
     * @brief * 유저 조회
     */
    @Transactional(readOnly = true)
    public MemberDetail loadUserById(Long id) {
        var member = memberRepository.findById(id)
                .orElseThrow(() -> new NotExistMemberException(ErrorMessage.NOT_EXIST_MEMBER_EXCEPTION));

        return new MemberDetail(member);
    }

    /**
     * @param * JoinRequest : 로그인을 위한 email, password
     * @return * jwt token : 조회한 유저 정보 id, role 이용한 jwt token
     * @brief * 유저 로그인
     */
    @Transactional(readOnly = true)
    public String userLogin(JoinRequest joinRequest) {
        var member = memberRepository.findByEmail(new Email(joinRequest.getEmail()))
                .orElseThrow(
                        () -> new NotExistMemberException(ErrorMessage.NOT_EXIST_MEMBER_EXCEPTION)
                );

        if (!passwordEncoder.matches(joinRequest.getPassword(), member.getPassword())) {
            throw new InvalidPasswordMatchException(ErrorMessage.INVALID_PASSWORD_MATCH_EXCEPTION);
        }

        return tokenProvider.generateJwtToken(member.getId(), MemberType.USER.getValue());
    }

    public void parsingObjectNullCheck(MemberDetail detail) {
        if (detail == null) {
            throw new NotExistUserInfoException(ErrorMessage.NOT_EXIST_MEMBER_EXCEPTION);
        }
    }
}