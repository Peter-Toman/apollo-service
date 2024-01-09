package com.apollo.apolloservice.exception;

public class UserHasNoPostsException extends RuntimeException {

    private final Long userId;

    public UserHasNoPostsException(String message, Long userId) {
        super(message);
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
