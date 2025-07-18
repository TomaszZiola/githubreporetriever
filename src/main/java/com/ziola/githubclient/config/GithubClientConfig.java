package com.ziola.githubclient.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class GithubClientConfig {

    @Bean
    public RestClient githubRestClient(@Value("${github.api.url}") String baseUrl) {
        return RestClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("Accept", "application/vnd.github.v3+json")
                .build();
    }
}
