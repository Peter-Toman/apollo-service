package com.apollo.apolloservice.model.entity;

import com.apollo.apolloservice.model.dto.PostDto;
import jakarta.persistence.*;
@Entity
@Table(name = "post")
public class Post {

    @Id
    private Long id;

    private Long userId;

    private String title;

    private String body;

    public Post() {}

    public Post(Long id, Long userId, String title, String body) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    public Post(PostDto postDto) {
        this.id = postDto.id();
        this.userId = postDto.userId();
        this.title = postDto.title();
        this.body = postDto.body();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String text) {
        this.body = text;
    }
}
