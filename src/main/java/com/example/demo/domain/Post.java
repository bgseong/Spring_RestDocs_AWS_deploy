package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String body;

    @Column
    private Long create_time;

    @Column
    private Long modify_time;

    @PrePersist
    public void onPrePersist(){
        this.create_time = System.currentTimeMillis();
    }

    @PreUpdate
    public void onUpdate(){
        this.modify_time = System.currentTimeMillis();
    }
}
