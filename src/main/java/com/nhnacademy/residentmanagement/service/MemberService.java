package com.nhnacademy.residentmanagement.service;

import com.nhnacademy.residentmanagement.domain.request.MemberCreateRequest;
import com.nhnacademy.residentmanagement.domain.request.MemberId;
import com.nhnacademy.residentmanagement.entity.Authority;
import com.nhnacademy.residentmanagement.entity.Member;
import com.nhnacademy.residentmanagement.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 회원가입 서비스.
 */
@Service
@AllArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입 서비스.
     *
     * @param request 회원가입 정보
     * @return Member ID
     */
    @Transactional
    public MemberId createMember(MemberCreateRequest request) {
        Member member = new Member();
        member.setId(request.getId());
        member.setName(request.getName());
        member.setPassword(passwordEncoder.encode(request.getPassword()));

        Authority authority = new Authority();
        authority.setMember(member);
        authority.setAuthority(request.getAuthority());

        member.setAuthority(authority);

        memberRepository.saveAndFlush(member);

        MemberId memberId = new MemberId();
        memberId.setId(member.getId());

        return memberId;
    }

}
