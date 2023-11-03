package com.example.demo.domain;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    private Long id;

    private String title;

    private String body;

    private Long create_time;

    private Long modify_time;


}
