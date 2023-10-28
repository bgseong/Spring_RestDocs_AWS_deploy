package com.example.demo;

import com.example.demo.domain.Post;
import com.example.demo.dto.PostRequest;
import com.example.demo.repository.PostRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.restdocs.RestDocsAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "api.minlog.com", uriPort = 443)
@ExtendWith(RestDocumentationExtension.class)
class DemoApplicationTests {
	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper objectMapper;
	@Autowired
	PostRepository postRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void create() throws JsonProcessingException {
		PostRequest request = PostRequest.builder()
				.title("오늘할일")
				.body("먹고자고싸기")
				.build();

		String requestJson = objectMapper.writeValueAsString(request);


//		this.mockMvc.perform(RestDocumentationRequestBuilders.post("api/test/post")
//				.contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON)
//				.content(requestJson))
//				.andDo()
	}
	@Test
	void read() {
//		Post post = postRepository.findById(1L).get();
//		System.out.println(post.getTitle());

	}
	@Test
	@Transactional
	void update() {

	}
	@Test
	void delete() {

	}
}
