package com.apollo.apolloservice.service.impl;

import com.apollo.apolloservice.exception.*;
import com.apollo.apolloservice.model.dto.PostDto;
import com.apollo.apolloservice.model.dto.PostListDto;
import com.apollo.apolloservice.model.dto.UserDto;
import com.apollo.apolloservice.model.entity.Post;
import com.apollo.apolloservice.repository.PostRepository;
import com.apollo.apolloservice.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ExternalDataServiceImpl externalDataService;

    public PostServiceImpl(PostRepository postRepository, ExternalDataServiceImpl externalDataService) {
        this.postRepository = postRepository;
        this.externalDataService = externalDataService;
    }

    /**
     *  Tries to retrieve post from database. If none is found
     * */
    @Override
    public PostDto getPost(Long postId) {
        Optional<Post> dbEntry = postRepository.findById(postId);
        if (dbEntry.isPresent()) {
            return new PostDto(dbEntry.get());
        }
        ResponseEntity<PostDto> externalPost = externalDataService.retrieveExternalPost(postId);
        if (externalPost.getBody() != null) {
            Post savedExternalPost = postRepository.save(new Post(externalPost.getBody()));
            return new PostDto(savedExternalPost);
        }
        throw new PostNotExistException("Post with this id does not exists", postId);
    }

    @Override
    public List<PostDto> getPostsByUserId(Long userId) {
        validateUser(userId);
        List<Post> dbEntries = postRepository.findAllByUserId(userId);
        if (dbEntries.size() > 0) {
            return dbEntries.stream().map(PostDto::new).collect(Collectors.toList());
        }
        ResponseEntity<PostDto[]> externalEntries = externalDataService.retrieveExternalPostsByUserId(userId);
        if (externalEntries.getStatusCode().is2xxSuccessful() && externalEntries.getBody() != null) {
            List<PostDto> externalPosts = Arrays.asList(externalEntries.getBody());
            saveNewExternalPosts(externalPosts);
            return externalPosts;
        }
        throw new UserHasNoPostsException("This user has no posts", userId);
    }

    private void saveNewExternalPosts(List<PostDto> externalPosts) {
        List<Long> idsToCheck = externalPosts.stream().map(PostDto::id).toList();
        List<Long> existingIds = postRepository.findAllIdsInList(idsToCheck);
        List<Long> idsToSave = idsToCheck.stream().filter( id -> !existingIds.contains(id)).toList();
        List<PostDto> postsDtoToSave = externalPosts.stream().filter( postDto -> idsToSave.contains(postDto.id())).toList();
        List<Post> postsToSave = postsDtoToSave.stream().map(Post::new).toList();
        postRepository.saveAll(postsToSave);
    }

    @Override
    public void createNewPost(PostDto postDto) {
        validateUser(postDto.userId());
        Optional<Post> dbEntry = postRepository.findById(postDto.id());
        if (dbEntry.isPresent()) {
            throw new PostIdConflictException("Id for this post is already used.", postDto.id());
        }
        checkRequiredAttributes(postDto);
        Post post = new Post(postDto);
        postRepository.save(post);
    }

    @Override
    public void updatePost(PostDto postDto) {
        Optional<Post> dbEntry = postRepository.findById(postDto.id());
        if (dbEntry.isEmpty()) {
            throw new PostNotExistException("Post with this id does not exist. Update not possible.", postDto.id());
        }
        checkRequiredAttributes(postDto);
        Post post = dbEntry.get();
        post.setTitle(postDto.title());
        post.setBody(postDto.body());
        postRepository.save(post);
    }

    @Override
    public void deletePost(Long id) {
        Optional<Post> dbEntry = postRepository.findById(id);
        if (dbEntry.isEmpty()) {
            throw new PostNotExistException("Post with this id does not exist. Delete not possible.", id);
        }
        postRepository.deleteById(id);
    }

    private void validateUser(Long userId) {
        if (!validateUserId(userId)) {
            throw new UserNotExistsException("User with this id does not exists", userId);
        }
    }

    private boolean validateUserId(Long userId) {
        ResponseEntity<UserDto> userResponse = externalDataService.retrieveExternalUser(userId);
        return userResponse.getStatusCode().is2xxSuccessful();
    }

    private void checkRequiredAttributes(PostDto postDto) {
        // Title and body cannot be null, empty or blank.
        if (postDto.title() == null || postDto.title().isBlank() || postDto.body() == null || postDto.body().isBlank()) {
            throw new PostEmptyAttributeException("One of the attributes is empty or blank.");
        }
    }
}
