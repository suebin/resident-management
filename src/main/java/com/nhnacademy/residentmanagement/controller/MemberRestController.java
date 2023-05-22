package com.nhnacademy.residentmanagement.controller;

import com.nhnacademy.residentmanagement.domain.request.MemberCreateRequest;
import com.nhnacademy.residentmanagement.domain.request.MemberId;
import com.nhnacademy.residentmanagement.service.MemberService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 회원가입을 위한 Rest Controller.
 */
@RestController
@RequestMapping("/members")
public class MemberRestController {
    private final MemberService memberService;

    public MemberRestController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public MemberId createMember(@RequestBody MemberCreateRequest request) {
        return memberService.createMember(request);
    }
}
