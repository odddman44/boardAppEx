package com.study;

import com.study.member.Gender;
import com.study.member.Member;
import com.study.member.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    // 회원 정보 생성
    @Test
    void saveMember() {
        Member saveParams = Member.builder()
                .loginId("test001")
                .password("sso132")
                .name("홍길동")
                .gender(Gender.M)
                .birthday(LocalDate.of(1994, 04, 04))
                .deleteYn('N')
                .build();

        Member member = memberRepository.save(saveParams);
        Assertions.assertEquals(member.getLoginId(), "test001");
    }

    // 전체 회원 조회
    @Test
    void findAllMember() {
        memberRepository.findAll();
    }

    // 회원 상세정보 조회
    @Test
    void findMemberById() {
        Member member = memberRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException());
        Assertions.assertEquals(member.getLoginId(), "test001");
    }

    // 회원 정보 삭제
    @Test
    void deleteMemberById() {
        memberRepository.deleteById(1L);
    }
}
