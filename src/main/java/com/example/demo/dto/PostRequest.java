package com.example.demo.dto;

import com.example.demo.domain.Post;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;

@Data
public class PostRequest {
    private String title;

    private String body;

    public Post toEntity(){
        return Post.builder()
                .title(this.title)
                .body(this.body)
                .build();
    }

}
