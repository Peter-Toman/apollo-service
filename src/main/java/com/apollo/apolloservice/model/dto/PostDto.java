package com.apollo.apolloservice.model.dto;

public record PostDto(
        Long id,
        Long userId,
        String title,
        String body
) {
}
