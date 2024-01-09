package com.apollo.apolloservice.controller;

import com.apollo.apolloservice.exception.*;
import com.apollo.apolloservice.model.dto.PostDto;
import com.apollo.apolloservice.model.dto.PostUpdateDto;
import com.apollo.apolloservice.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {

    private final Logger LOG = LoggerFactory.getLogger(PostController.class);


    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(postService.getPost(id));
        } catch (PostNotExistException ex) {
            LOG.error(ex.getMessage(), ex);
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDto>> getPostsByUserId(@PathVariable Long userId) {
        try {
            List<PostDto> posts = postService.getPostsByUserId(userId);
            return ResponseEntity.ok(posts);
        } catch (UserNotExistsException | UserHasNoPostsException ex) {
            LOG.error(ex.getMessage(), ex);
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(path = "", consumes = "application/json")
    public ResponseEntity<Void> createPost(@RequestBody PostDto requestPostDto) {
        try {
            postService.createNewPost(requestPostDto);
            return ResponseEntity.ok().build();
        } catch (UserNotExistsException | PostIdConflictException | PostEmptyAttributeException ex) {
            LOG.error(ex.getMessage(), ex);
            return ResponseEntity.badRequest().build();
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePost(@PathVariable Long id, @RequestBody PostUpdateDto postUpdateDto) {
        try {
            PostDto postDto = new PostDto(id, postUpdateDto);
            postService.updatePost(postDto);
            return ResponseEntity.ok().build();
        } catch (PostNotExistException ex) {
            LOG.error(ex.getMessage(), ex);
            return ResponseEntity.notFound().build();
        } catch (PostEmptyAttributeException ex) {
            LOG.error(ex.getMessage(), ex);
            return ResponseEntity.badRequest().build();
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        try {
            postService.deletePost(id);
            return ResponseEntity.ok().build();
        } catch (PostNotExistException ex) {
            LOG.error(ex.getMessage(), ex);
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            return ResponseEntity.internalServerError().build();
        }
    }

}
