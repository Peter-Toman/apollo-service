package com.apollo.apolloservice.model.dto;

public record PostUpdateDto(
        Long userId,
        String title,
        String body
) {
}
