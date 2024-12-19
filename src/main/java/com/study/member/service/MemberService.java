package com.study.member.service;

import com.study.exception.CustomException;
import com.study.exception.ErrorCode;
import com.study.member.Member;
import com.study.member.MemberRepository;
import com.study.member.dto.MemberRequestDto;
import com.study.member.dto.MemberResponseDto;
import com.study.utils.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 정보 저장 (회원가입)
     */
    @Transactional
    public Long saveMember(final MemberRequestDto params) {
        Member entity = memberRepository.save(params.toEntity());
        return entity.getId();
    }

    /**
     * 회원 정보 수정
     */
    @Transactional
    public Long updateMember(final Long id, final MemberRequestDto params) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        member.update(params);
        return member.getId();
    }

    /**
     * 회원 정보 수정 (비밀번호 변경)
     */
    @Transactional
    public Long updatePassword(final Long id, final String rawPassword) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        // 새 비밀번호 해싱
        String salt = PasswordEncoder.generateSalt();
        String hashedPassword = PasswordEncoder.encode(rawPassword, salt);

        member.updatePassword(hashedPassword);
        return member.getId();
    }

    /**
     * 회원 정보 삭제 (회원 탈퇴)
     */
    @Transactional
    public Long deleteMember(final Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        member.delete();
        return member.getId();
    }

    /**
     * 회원 상세정보 조회
     */
    public MemberResponseDto findMemberByLoginId(final String loginId) {
        Member member = memberRepository.findByLoginIdAndDeleteYn(loginId, 'N')
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        return new MemberResponseDto(member);
    }

    /**
     * 전체 회원 조회
     */
    public List<MemberResponseDto> findAll() {
        return memberRepository.findAllByDeleteYn('N')
                .stream()
                .map(MemberResponseDto::new)
                .collect(Collectors.toList());
    }

    /**
     * 회원 수 카운팅 (ID 중복 체크)
     */
    public int countByLoginId(final String loginId) {
        return memberRepository.countByLoginId(loginId);
    }
}
