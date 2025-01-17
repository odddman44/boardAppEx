package com.study.post.service;

import com.study.utils.paging.CommonParams;
import com.study.utils.paging.Pagination;
import com.study.post.Post;
import com.study.post.PostRepository;
import com.study.post.dto.PostRequestDto;
import com.study.post.dto.PostResponseDto;
import com.study.exception.CustomException;
import com.study.exception.ErrorCode;
import com.study.post.model.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

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

    /**
     * 게시글 리스트 조회 - (With. pagination information)
     */
    public Map<String, Object> findAll(CommonParams params) {

        // 게시글 수 조회
        int count = postMapper.count(params);

        // 등록된 게시글이 없는 경우, 로직 종료
        if (count < 1) {
            Map<String, Object> response = new HashMap<>();
            response.put("params", params);
            response.put("list", Collections.emptyList()); // 빈 리스트를 명시적으로 설정
            return response;
        }

        // 페이지네이션 정보 계산
        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);

        // 게시글 리스트 조회
        List<PostResponseDto> list = postMapper.findAll(params);

        // 데이터 반환
        Map<String, Object> response = new HashMap<>();
        response.put("params", params);
        response.put("list", list);
        return response;
    }

}
