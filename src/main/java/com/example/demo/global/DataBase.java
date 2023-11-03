package com.example.demo.global;

import com.example.demo.domain.Post;
import com.example.demo.dto.UpdateRequest;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;

@Component
public class DataBase {
    private HashMap<Long,Post> data = new HashMap<>();

    @PostConstruct
    public void init() {
        System.out.println("Init !!");
    }

    public void resetWhiteList() {
        data.clear();
    }

    public Post save(Post post){
        Long id;
        if(this.data.isEmpty()){
            id = 1L;
        }
        else {
            id = Collections.max(this.data.keySet())+1L;
        }
        post.setId(id);
        post.setCreate_time(System.currentTimeMillis());
        this.data.put(id,post);
        return post;
    }

    public Post read(Long id){
        return this.data.get(id);
    }

    public Post update(Long id, UpdateRequest request){
        Post post = this.data.get(id);
        post.setTitle(request.getTitle());
        post.setBody(request.getBody());
        post.setModify_time(System.currentTimeMillis());
        this.data.put(id,post);
        return post;
    }

    public void delete(Long id){
        this.data.remove(id);
    }

}
