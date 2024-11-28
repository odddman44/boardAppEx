package com.study.domain.post.dto;


import com.study.domain.post.Post;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostRequestDto {

    private String title;       // 제목
    private String content;     // 내용
    private String writer;      // 작성자
    private char deleteYn;   // 삭제 여부
    private char noticeYn;   // 공지글 여부

    public Post toEntity() {
        return Post.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .hits(0)
                .deleteYn(deleteYn)
                .noticeYn(noticeYn)
                .build();
    }
}
