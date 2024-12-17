package com.study.post.model;

import com.study.paging.CommonParams;
import com.study.post.dto.PostResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

    /**
     * 게시글 수 조회
     */
    int count(final CommonParams params);

    /**
     * 게시글 리스트 조회
     */
    List<PostResponseDto> findAll(final CommonParams params);
}
