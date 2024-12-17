package com.study.member;

import com.study.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tb_member")
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;                // 회원번호 (PK)

    @Column(name = "login_id")
    private String loginId;         // 로그인 ID

    @Column(name = "password")
    private String password;        // 비밀번호

    @Column(name = "name")
    private String name;            // 이름

    @Column(name ="gender")
    private Gender gender;          // 성별

    @Column(name = "birthday")
    private LocalDate birthday;     // 생년월일

    @Column(name = "delete_yn", nullable = false)
    private char deleteYn;       // 삭제 여부

    @Builder
    public Member(String loginId, String password, String name, Gender gender, LocalDate birthday, char deleteYn) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.deleteYn = deleteYn;
    }
}
