package com.apollo.apolloservice.model.dto;

import java.util.List;

public record PostListDto(
    List<PostDto> posts
) {
}
