package com.apollo.apolloservice.controller;

import com.apollo.apolloservice.model.dto.PostDeleteDto;
import com.apollo.apolloservice.model.dto.PostDto;
import com.apollo.apolloservice.model.dto.PostUpdateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/post")
public class PostController {

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable Long id) {
        //TODO: retrieve post
        PostDto postDto = new PostDto(id,7L, "test title", "test body");
        return ResponseEntity.ok(postDto);
    }

    @PostMapping("")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto requestPostDto) {
        //TODO: create post
        PostDto responsePostDto = new PostDto(requestPostDto.id(),requestPostDto.userId(), requestPostDto.title(), requestPostDto.body());
        return ResponseEntity.ok(responsePostDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Long id, @RequestBody PostUpdateDto postUpdateDto) {
        //TODO: update post
        PostDto responsePostDto = new PostDto(id, postUpdateDto.userId(), postUpdateDto.title(), postUpdateDto.body());
        return ResponseEntity.ok(responsePostDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PostDeleteDto> deletePost(@PathVariable Long id) {
        //TODO: delete post
        PostDeleteDto postDeleteDto = new PostDeleteDto(id);
        return ResponseEntity.ok(postDeleteDto);
    }

}
