package com.apollo.apolloservice.exception;

public class PostIdConflictException extends RuntimeException {

    private final Long postId;

    public PostIdConflictException(String message, Long conflictId) {
        super(message);
        this.postId = conflictId;
    }

    public Long getPostId() {
        return postId;
    }

}
