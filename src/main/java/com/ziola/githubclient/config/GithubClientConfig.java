package com.ziola.githubclient.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClient.Builder;

import static com.ziola.githubclient.constants.Constants.ACCEPT;
import static com.ziola.githubclient.constants.Constants.GITHUB_API_ACCEPT_HEADER;

@Configuration
public class GithubClientConfig {

    @Bean
    public RestClient githubRestClient(Builder builder, @Value("${github.api.url}") String baseUrl) {
        return builder
                .baseUrl(baseUrl)
                .defaultHeader(ACCEPT, GITHUB_API_ACCEPT_HEADER)
                .build();
    }
}
