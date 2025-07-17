package com.ziola.githubclient.service;

import com.ziola.githubclient.client.GithubClient;
import com.ziola.githubclient.dto.github.GithubRepository;
import com.ziola.githubclient.dto.response.Branch;
import com.ziola.githubclient.dto.response.Repository;
import com.ziola.githubclient.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.NotFound;

import java.util.List;

@Service
public class GitHubService {
    private final GithubClient githubClient;

    public GitHubService(GithubClient githubClient) {
        this.githubClient = githubClient;
    }


    public List<Repository> retrieveRepositories(String username) {
        try {
            List<GithubRepository> nonForkedRepos = githubClient.getRepositories(username)
                    .stream()
                    .filter(repo -> !repo.fork())
                    .toList();
            return mapToRepositoriesWithBranches(username, nonForkedRepos);
        } catch (NotFound errorException) {
            throw new UserNotFoundException("User '" + username + "' not found");
        }
    }

    private List<Repository> mapToRepositoriesWithBranches(String username, List<GithubRepository> nonForkedRepos) {
        return nonForkedRepos
                .stream()
                .map(githubRepository -> new Repository(
                        githubRepository.name(),
                        githubRepository.owner().login(),
                        retrieveBranches(username, githubRepository.name())
                ))
                .toList();
    }

    private List<Branch> retrieveBranches(String username, String repoName) {
        return githubClient.getBranches(username, repoName)
                .stream()
                .map(githubBranch -> new Branch(
                        githubBranch.name(),
                        githubBranch.commit().sha()))
                .toList();
    }
}
