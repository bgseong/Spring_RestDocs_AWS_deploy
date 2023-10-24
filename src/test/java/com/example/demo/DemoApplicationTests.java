package com.example.demo;

import com.example.demo.domain.Post;
import com.example.demo.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	PostRepository postRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void create() {
		Post post = Post.builder()
				.id(1L)
				.title("오늘할일")
				.body("먹고자고싸기")
				.create_time(1635123)
				.build();

		postRepository.save(post);
	}
	@Test
	void read() {
		Post post = postRepository.findById(1L).get();
		System.out.println(post.getTitle());

	}
	@Test
	@Transactional
	void update() {


	}
	@Test
	void delete() {
		postRepository.deleteById(1L);


	}
}
