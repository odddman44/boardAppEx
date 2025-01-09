package com.study.member.dto;

import com.study.member.Gender;
import com.study.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberRequestDto {

    private String loginId;         // 로그인ID
    private String password;        // 비밀번호
    private String name;            // 이름
    private Gender gender;          // 성별
    private LocalDate birthday;     // 생년월일

    public Member toEntity() {
        return Member.builder()
                .loginId(this.loginId)
                .password(this.password)   // 해싱된 비밀번호
                .name(this.name)
                .gender(this.gender)
                .birthday(this.birthday)
                .deleteYn('N') // 신규 생성시 삭제 여부
                .build();
    }
}
