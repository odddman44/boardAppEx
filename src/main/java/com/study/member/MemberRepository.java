package com.study.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByLoginIdAndDeleteYn(String loginId, char deleteYn);

    int countByLoginId(String loginId);

    List<Member> findAllByDeleteYn(char deleteYn);
}
