package com.ziola.githubclient.controller;

import com.ziola.githubclient.dto.response.Repository;
import com.ziola.githubclient.service.GitHubService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GitHubController {
    private final GitHubService gitHubService;

    public GitHubController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @GetMapping("/{username}")
    public List<Repository> getRepositoriesWithBranches(@PathVariable String username) {
        return gitHubService.retrieveRepositories(username);
    }
}
