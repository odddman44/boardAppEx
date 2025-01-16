package com.study.member.controller;

import com.study.member.dto.MemberRequestDto;
import com.study.member.dto.MemberResponseDto;
import com.study.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    /**
     * 로그인
     */
    @PostMapping("/login")
    public MemberResponseDto login(@RequestBody MemberRequestDto params, HttpSession session) {
        MemberResponseDto member = memberService.login(params.getLoginId(), params.getPassword());
        session.setAttribute("loginMember", member); // 세션에 로그인 정보 저장
        session.setMaxInactiveInterval(60 * 300); // 세션 유지 시간 : 300분
        return member;
    }

    /**
     * 로그아웃 (Json 응답)
     */
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return ResponseEntity.ok("로그아웃 되었습니다.");
    }

    /**
     * 회원 정보 저장 (회원가입)
     */
    @PostMapping
    public Long saveMember(@RequestBody MemberRequestDto params) {
        return memberService.saveMember(params);
    }

    /**
     * 회원 상세정보 조회
     */
    @GetMapping("/{loginId}")
    public MemberResponseDto findMemberByLoginId(@PathVariable final String loginId) {
        return memberService.findMemberByLoginId(loginId);
    }

    /**
     * 회원 정보 수정
     */
    @PatchMapping("/{id}")
    public Long updateMember(@PathVariable final Long id, @RequestBody final MemberRequestDto params) {
        return memberService.updateMember(id, params);
    }

    /**
     * 회원 비밀번호 변경
     */
    @PatchMapping("/{id}/password")
    public Long updatePassword(@PathVariable Long id, @RequestBody String newPassword) {
        return memberService.updatePassword(id, newPassword);
    }

    /**
     * 회원 정보 삭제(회원 탈퇴)
     */
    @DeleteMapping("/{id}")
    public Long deleteMemberById(@PathVariable Long id) {
        return memberService.deleteMember(id);
    }

    /**
     * 회원 수 카운팅 (ID 중복 체크)
     */
    @GetMapping("/count")
    public int countByLoginId(@RequestParam String loginId) {
        return memberService.countByLoginId(loginId);
    }
}
