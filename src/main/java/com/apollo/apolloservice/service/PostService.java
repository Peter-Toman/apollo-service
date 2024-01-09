package com.apollo.apolloservice.service;

import com.apollo.apolloservice.model.dto.PostDto;

import java.util.List;

public interface PostService {

    public PostDto getPost(Long postId);

    public List<PostDto> getPostsByUserId(Long userId);

    public void createNewPost(PostDto postDto);

    public void updatePost(PostDto postDto);

    public void deletePost(Long id);

}
