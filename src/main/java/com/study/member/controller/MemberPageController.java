package com.study.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberPageController {

    /**
     * 로그인 페이지
     */
    @GetMapping("/login")
    public String openLoginPage() {
        return "member/login";
    }
}
