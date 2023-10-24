package com.example.demo.dto;

import com.example.demo.domain.Post;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostResponse {
    private Long id;

    private String title;

    private String body;

    private Integer create_time;

    private Integer modify_time;


}
