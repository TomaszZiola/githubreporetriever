package com.ziola.githubclient.client;

import com.ziola.githubclient.dto.Branch;
import com.ziola.githubclient.dto.Repository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class GithubClient {
    private final RestClient restClient;

    public GithubClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<Repository> getRepositories(String username) {
        return restClient.get()
                .uri("/users/{username}/repos", username)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    public List<Branch> getBranches(String username, String repoName) {
        return restClient.get()
                .uri("/repos/{username}/{repoName}/branches", username, repoName)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }
}
