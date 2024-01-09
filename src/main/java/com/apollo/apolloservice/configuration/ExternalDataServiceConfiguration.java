package com.apollo.apolloservice.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("app.external-api")
public class ExternalDataServiceConfiguration {

    private String scheme;

    private String baseUrl;

    private String postsPath;

    private String usersPath;

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getPostsPath() {
        return postsPath;
    }

    public void setPostsPath(String postsPath) {
        this.postsPath = postsPath;
    }

    public String getUsersPath() {
        return usersPath;
    }

    public void setUsersPath(String usersPath) {
        this.usersPath = usersPath;
    }
}
