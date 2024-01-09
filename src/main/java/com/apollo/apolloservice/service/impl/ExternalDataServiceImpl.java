package com.apollo.apolloservice.service.impl;

import com.apollo.apolloservice.configuration.ExternalDataServiceConfiguration;
import com.apollo.apolloservice.model.dto.PostDto;
import com.apollo.apolloservice.model.dto.UserDto;
import com.apollo.apolloservice.service.ExternalDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;


@Service
public class ExternalDataServiceImpl implements ExternalDataService {

    private final ExternalDataServiceConfiguration externalDataServiceConfiguration;
    private final RestClient restClient;

    public ExternalDataServiceImpl(ExternalDataServiceConfiguration externalDataServiceConfiguration) {
        this.externalDataServiceConfiguration = externalDataServiceConfiguration;
        this.restClient = RestClient.builder()
                .requestFactory(new HttpComponentsClientHttpRequestFactory())
                .defaultHeader("Accept", "application/json")
                .build();
    }

    @Override
    public ResponseEntity<PostDto> retrieveExternalPost(Long id) {
        String resourceUri = getUriToResource(externalDataServiceConfiguration.getPostsPath()).path(id.toString()).build().toUriString();
        return restClient.get().uri(resourceUri).retrieve().toEntity(PostDto.class);
    }

    @Override
    public ResponseEntity<UserDto> retrieveExternalUser(Long id) {
        String resourceUri = getUriToResource(externalDataServiceConfiguration.getUsersPath()).path("/" + id.toString()).build().toUriString();
        return restClient.get().uri(resourceUri).retrieve().toEntity(UserDto.class);
    }

    @Override
    public ResponseEntity<PostDto[]> retrieveExternalPostsByUserId(Long id) {
        String resourceUri = getUriToResource(externalDataServiceConfiguration.getPostsPath()).queryParam("userId", id).build().toUriString();
        return restClient.get().uri(resourceUri).retrieve().toEntity(PostDto[].class);
    }

    private UriComponentsBuilder getUriToResource(String path) {
        return UriComponentsBuilder.newInstance().scheme(externalDataServiceConfiguration.getScheme())
                .host(externalDataServiceConfiguration.getBaseUrl()).path(path);
    }
}
