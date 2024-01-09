package com.apollo.apolloservice.model.entity;

import jakarta.persistence.*;
@Entity
@Table(name = "post")
public class Post {

    @Id
    private Long id;

    private Long userId;

    private String title;

    private String text;

    public Post() {}

    public Post(Long id, Long userId, String title, String text) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.text = text;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
