package com.study.domain.post.dto;

import com.study.domain.post.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {

    private Long id; // PK
    private String title; // 제목
    private String content; // 내용
    private String writer; // 작성자
    private int hits; // 조회 수
    private char deleteYn; // 삭제 여부
    private char noticeYn; // r공지글 여부
    private LocalDateTime createdDate; // 생성일
    private LocalDateTime modifiedDate; // 수정일

    public PostResponseDto(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.writer = entity.getWriter();
        this.hits = entity.getHits();
        this.deleteYn = entity.getDeleteYn();
        this.noticeYn = entity.getNoticeYn();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }
}
