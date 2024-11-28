package com.study.domain.post;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     * 게시글 리스트 조회 - (삭제 여부 확인)
     */
    List<Post> findAllByDeleteYn(final char deleteYn, final Sort sort);
}
