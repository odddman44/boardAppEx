package com.study.member.service;

import com.study.exception.CustomException;
import com.study.exception.ErrorCode;
import com.study.member.Member;
import com.study.member.MemberRepository;
import com.study.member.dto.MemberRequestDto;
import com.study.member.dto.MemberResponseDto;
import com.study.utils.MyPasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    //private final MyPasswordEncoder passwordEncoder;


    /**
     * 회원 정보 저장 (회원가입)
     */
    @Transactional
    public Long saveMember(final MemberRequestDto params) {
        // 비밀번호 해싱 및 Salt 생성
        //String salt = passwordEncoder.generateSalt();
        String hashedPassword = encodePassword(params.getPassword());
        // 엔터티 생성
        Member entity = Member.builder()
                .loginId(params.getLoginId())
                .password(hashedPassword)
                .name(params.getName())
                .gender(params.getGender())
                .birthday(params.getBirthday())
                .deleteYn('N')
                .build();

        memberRepository.save(entity);
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
        //String salt = passwordEncoder.generateSalt();
        String hashedPassword = encodePassword(rawPassword);

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

    /**
     * 로그인 처리 메서드
     * @param loginId  사용자 ID
     * @param password 사용자 입력 비밀번호
     * @return MemberResponseDto (로그인 성공 시 사용자 정보 반환)
     */
    public MemberResponseDto login(final String loginId, final String password) {
        // 1. 사용자 정보 조회
        Member member = memberRepository.findByLoginIdAndDeleteYn(loginId, 'N')
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        // 2. 비밀번호 검증
        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }

        // 3. 로그인 성공 시 사용자 정보 반환
        return new MemberResponseDto(member);
    }


    private String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
}
