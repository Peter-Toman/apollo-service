package com.apollo.apolloservice.model.dto;

import com.apollo.apolloservice.model.entity.Post;

public record PostDto(
        Long id,
        Long userId,
        String title,
        String body
) {

    public PostDto(Post post) {
        this(post.getId(), post.getUserId(), post.getTitle(), post.getBody());
    }

    public PostDto(Long id, PostUpdateDto postUpdateDto) {
        this(id, null, postUpdateDto.title(), postUpdateDto.body());
    }
}
