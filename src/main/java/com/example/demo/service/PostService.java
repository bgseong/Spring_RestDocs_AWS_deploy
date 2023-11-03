package com.example.demo.service;

import com.example.demo.domain.Post;
import com.example.demo.dto.PostRequest;
import com.example.demo.dto.PostResponse;
import com.example.demo.dto.UpdateRequest;
import com.example.demo.global.DataBase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class PostService {

    @Autowired
    private DataBase dataBase;

    public void create(PostRequest request){
        Post post = request.toEntity();
        dataBase.save(post);

    }

    public PostResponse read(Long id){
        Post post = dataBase.read(id);
        PostResponse response = new PostResponse(post);
        return response;
    }

    public PostResponse update(Long id, UpdateRequest updateRequest){
        Post post = dataBase.update(id,updateRequest);
        PostResponse response = new PostResponse(post);
        return response;
    }

    public void delete(Long id){
        dataBase.delete(id);
    }
}
