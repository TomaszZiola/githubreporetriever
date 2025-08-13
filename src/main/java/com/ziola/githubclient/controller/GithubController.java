package com.ziola.githubclient.controller;

import com.ziola.githubclient.api.dto.GithubRepositoryResponse;
import com.ziola.githubclient.service.GithubService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/github/users")
public class GithubController {
    private final GithubService githubService;

    public GithubController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/{username}")
    public List<GithubRepositoryResponse> getRepositoriesWithBranches(@PathVariable String username) {
        return githubService.retrieveRepositories(username);
    }
}
