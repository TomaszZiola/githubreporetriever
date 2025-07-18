package com.ziola.githubclient.controller;

import com.ziola.githubclient.dto.response.Repository;
import com.ziola.githubclient.service.GithubService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GithubController {
    private final GithubService gitHubService;

    public GithubController(GithubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @GetMapping("/{username}")
    public List<Repository> getRepositoriesWithBranches(@PathVariable String username) {
        return gitHubService.retrieveRepositories(username);
    }
}
