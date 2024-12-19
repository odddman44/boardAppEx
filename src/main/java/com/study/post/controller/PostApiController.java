package com.study.post.controller;


import com.study.utils.paging.CommonParams;
import com.study.post.dto.PostRequestDto;
import com.study.post.dto.PostResponseDto;
import com.study.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;

    /**
     * 게시글 생성
     */
    @PostMapping("/posts")
    public Long save(@RequestBody final PostRequestDto params) {
        return postService.save(params);
    }

    /**
     * 게시글 수정
     */
    @PatchMapping("/posts/{id}")
    public Long save(@PathVariable final Long id, @RequestBody final PostRequestDto params) {
        return postService.update(id, params);
    }

    /**
     * 게시글 삭제
     */
    @DeleteMapping("/posts/{id}")
    public Long delete(@PathVariable final Long id) {
        return postService.delete(id);
    }

    /**
     * 게시글 리스트 조회
     */
    @GetMapping("/posts")
    public Map<String, Object> findAll(final CommonParams params) {
        return postService.findAll(params);
    }

    /**
     * 게시글 상세정보 조회
     */
    @GetMapping("/posts/{id}")
    public PostResponseDto findById(@PathVariable final Long id) {
        return postService.findById(id);
    }

}
