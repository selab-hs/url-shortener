package com.urlshortener.member.repository;

import com.urlshortener.member.domain.Member;
import com.urlshortener.member.domain.vo.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(Email email);
}
