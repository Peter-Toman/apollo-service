package com.apollo.apolloservice.service;

import com.apollo.apolloservice.model.dto.PostDto;
import com.apollo.apolloservice.model.dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface ExternalDataService {

    ResponseEntity<PostDto> retrieveExternalPost(Long id);

    ResponseEntity<UserDto> retrieveExternalUser(Long id);

    ResponseEntity<PostDto[]> retrieveExternalPostsByUserId(Long id);

}
