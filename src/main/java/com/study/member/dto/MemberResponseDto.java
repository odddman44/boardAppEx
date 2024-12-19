package com.study.member.dto;

import com.study.member.Gender;
import com.study.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponseDto {

    private Long id;                    // 회원번호 (PK)
    private String loginId;             // 로그인 ID
    private String name;                // 이름
    private Gender gender;              // 성별
    private LocalDate birthday;         // 생년월일
    private char deleteYn;              // 삭제 여부
    private LocalDateTime createDate;   // 생성일시
    private LocalDateTime modifiedDate; // 수정일시

    public MemberResponseDto(Member entity) {
        this.id = entity.getId();
        this.loginId = entity.getLoginId();
        this.name = entity.getName();
        this.gender = entity.getGender();
        this.birthday = entity.getBirthday();
        this.deleteYn = entity.getDeleteYn();
        this.createDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }
}
