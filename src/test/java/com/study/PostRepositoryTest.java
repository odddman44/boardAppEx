package com.study;

import com.study.domain.post.Post;
import com.study.domain.post.PostRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.persistence.EntityNotFoundException;

@SpringBootTest
public class PostRepositoryTest {

	@Autowired
	PostRepository postRepository;

	@Autowired
	private ApplicationContext context;

	// 게시글 생성
	@Test
	void savePost() {
		Post saveParams = Post.builder()
				.title("게시글 제목")
				.content("게시글 내용")
				.writer("작성자1")
				.hits(0)
				.noticeYn(false)
				.deleteYn(false)
				.build();

		Post post = postRepository.save(saveParams);
		Assertions.assertEquals(post.getTitle(), "게시글 제목");
	}

	// 전체 게시글 조회
	@Test
	void findAllPosts() {
		postRepository.findAll();
	}

	// 특정 게시글 조회
	@Test
	void findPostById() {
		Post post = postRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException());
		Assertions.assertEquals(post.getWriter(), "작성자1");
	}

	// 게시글 삭제
	@Test
	void deletePostById() {
		postRepository.deleteById(1L);
	}



}
