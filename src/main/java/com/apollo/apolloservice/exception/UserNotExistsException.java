package com.apollo.apolloservice.exception;

public class UserNotExistsException extends RuntimeException {

    private final Long userId;

    public UserNotExistsException(String message, Long userId) {
        super(message);
        this.userId = userId;

    }

    public Long getUserId() {
        return userId;
    }
}
