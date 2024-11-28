package com.study.domain.post;


import com.study.domain.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tb_post")
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                // PK

    @Column(nullable = false, length = 100)
    private String title;           // 제목

    @Column(nullable = false, length = 100)
    private String content;         // 내용

    @Column(nullable = false, length = 20)
    private String writer;          // 작성자

    @Column(nullable = false)
    private int hits;            // 조회 수

    @Column(name = "notice_yn", nullable = false)
    private char noticeYn;       // 공지글 여부

    @Column(name = "delete_yn", nullable = false)
    private char deleteYn;       // 삭제 여부

    @Builder
    public Post(String title, String content, String writer, int hits, char noticeYn, char deleteYn) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.hits = hits;
        this.noticeYn = noticeYn;
        this.deleteYn = deleteYn;
    }

    // 게시글 수정
    public void update(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    // 조회 수 증가
    public void increaseHits() {
        this.hits++;
    }

    // 게시글 삭제
    public void delete() {
        this.deleteYn = 'Y';
    }
}
