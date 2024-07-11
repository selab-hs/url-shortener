package com.urlshortener.member.repository;

import com.urlshortener.member.domain.RegisteredMember;
import com.urlshortener.member.domain.vo.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegisterMemberRepository extends JpaRepository<RegisteredMember, Long> {
    Optional<RegisteredMember> findByEmail(Email email);
}
