package com.apollo.apolloservice.exception;

public class PostNotExistException extends RuntimeException {

    private final Long postId;

    public PostNotExistException(String message, Long postId) {
        super(message);
        this.postId = postId;
    }

    public Long getPostId() {
        return postId;
    }

}
