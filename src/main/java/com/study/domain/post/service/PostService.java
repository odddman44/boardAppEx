package com.study.domain.post.service;

import com.study.domain.post.Post;
import com.study.domain.post.PostRepository;
import com.study.domain.post.dto.PostRequestDto;
import com.study.domain.post.dto.PostResponseDto;
import com.study.exception.CustomException;
import com.study.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    /**
     * 게시글 생성
     */
    @Transactional
    public Long save(final PostRequestDto params) {

        Post entity = postRepository.save(params.toEntity());
        return entity.getId();
    }

    /**
     * 게시글 수정
     */
    @Transactional
    public Long update(final Long id, final PostRequestDto params) {

        Post entity = postRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        entity.update(params.getTitle(), params.getContent(), params.getWriter());
        return id;
    }

    /**
     * 게시글 삭제
     */
    @Transactional
    public Long delete(final Long id) {

        Post entity = postRepository.findById(id).orElseThrow( () -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        entity.delete();
        return id;
    }

    /**
     * 게시글 리스트 조회
     */
    public List<PostResponseDto> findAll() {

        Sort sort = Sort.by(Direction.DESC, "id", "createdDate");
        List<Post> list = postRepository.findAll(sort);
        return list.stream().map(PostResponseDto::new).collect(Collectors.toList());
    }

    /**
     * 게시글 리스트 조회 - (삭제 여부 기준)
     */
    public List<PostResponseDto> findAllByDeleteYn(final char deleteYn) {

        Sort sort = Sort.by(Direction.DESC, "id", "createdDate");
        List<Post> list = postRepository.findAllByDeleteYn(deleteYn, sort);
        return list.stream().map(PostResponseDto::new).collect(Collectors.toList());
    }

    /**
     * 게시글 상세정보 조회
     */
    @Transactional
    public PostResponseDto findById(final Long id) {

        Post entity = postRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        entity.increaseHits();
        return new PostResponseDto(entity);
    }

}
