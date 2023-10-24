package com.example.demo.service;

import com.example.demo.domain.Post;
import com.example.demo.dto.PostRequest;
import com.example.demo.dto.PostResponse;
import com.example.demo.repository.PostRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    public void create(PostRequest request){
        Post post = request.toEntity();
        postRepository.save(post);
    }

    public PostResponse read(Long id){
        Post post = postRepository.findById(id).get();
    }
}
