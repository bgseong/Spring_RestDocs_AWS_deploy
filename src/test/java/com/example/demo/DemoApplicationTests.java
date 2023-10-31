package com.example.demo;

import com.example.demo.domain.Post;
import com.example.demo.dto.PostRequest;
import com.example.demo.dto.UpdateRequest;
import com.example.demo.repository.PostRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.sql.Update;
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
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.restdocs.payload.PayloadDocumentation;

import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "testServer", uriPort = 8080)
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
	void create() throws Exception {
		PostRequest request = PostRequest.builder()
				.title("글 제목")
				.body("글 내용")
				.build();

		String requestJson = objectMapper.writeValueAsString(request);

		mockMvc.perform(RestDocumentationRequestBuilders.post("/api/test/post")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andExpect(status().isOk())
				.andDo(document("post-create",
						PayloadDocumentation.requestFields(
								PayloadDocumentation.fieldWithPath("title").description("게시글 제목")
										.attributes((key("constraint").value("좋은 제목입력해주세요."))),
								PayloadDocumentation.fieldWithPath("body").description("게시글 내용").optional()
						)
				));
	}

	@Test
	void read() throws Exception {
		Post post = Post.builder()
				.title("제목")
				.body("본문")
				.build();

		Post savedPost = postRepository.save(post);


		mockMvc.perform(RestDocumentationRequestBuilders.get("/api/test/post/{postId}",savedPost.getId())
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(document("post-inquiry",
						RequestDocumentation.pathParameters(
								RequestDocumentation.parameterWithName("postId").description("게시글 ID")
						),
						PayloadDocumentation.responseFields(
								PayloadDocumentation.fieldWithPath("id").description("게시글 ID"),
								PayloadDocumentation.fieldWithPath("title").description("게시글 제목"),
								PayloadDocumentation.fieldWithPath("body").description("게시글 내용"),
								PayloadDocumentation.fieldWithPath("create_time").description("생성 시간"),
								PayloadDocumentation.fieldWithPath("modify_time").description("수정된 시간")
						)
				));

	}

	@Test
	@Transactional
	void update() throws Exception {
		Post post = Post.builder()
				.title("제목")
				.body("본문")
				.build();

		UpdateRequest request = UpdateRequest.builder()
				.title("제목 수정")
				.body("본문 수정")
				.build();

		String requestJson = objectMapper.writeValueAsString(request);

		Post savedPost = postRepository.save(post);
		savedPost.setTitle(request.getTitle());
		savedPost.setBody(request.getBody());
		savedPost.setModify_time(System.currentTimeMillis());

		mockMvc.perform(RestDocumentationRequestBuilders.put("/api/test/post/{postId}",savedPost.getId())
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andExpect(status().isOk())
				.andDo(document("post-update",
						PayloadDocumentation.requestFields(
								PayloadDocumentation.fieldWithPath("title").description("수정할 제목")
										.attributes((key("constraint").value("좋은 제목입력해주세요."))),
								PayloadDocumentation.fieldWithPath("body").description("수정할 내용").optional()
						),
						RequestDocumentation.pathParameters(
								RequestDocumentation.parameterWithName("postId").description("게시글 ID")
						),
						PayloadDocumentation.responseFields(
								PayloadDocumentation.fieldWithPath("id").description("게시글 ID"),
								PayloadDocumentation.fieldWithPath("title").description("수정된 제목"),
								PayloadDocumentation.fieldWithPath("body").description("수정된 내용"),
								PayloadDocumentation.fieldWithPath("create_time").description("생성 시간"),
								PayloadDocumentation.fieldWithPath("modify_time").description("수정된 시간")
						)
				));


	}

	@Test
	void delete() throws Exception{
		Post post = Post.builder()
				.title("제목")
				.body("본문")
				.build();

		Post savedPost = postRepository.save(post);
		postRepository.deleteById(savedPost.getId());
		mockMvc.perform(RestDocumentationRequestBuilders.delete("/api/test/post/{postId}",savedPost.getId())
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(document("post-delete",
						RequestDocumentation.pathParameters(
								RequestDocumentation.parameterWithName("postId").description("게시글 ID")
						)
				));

	}
}
