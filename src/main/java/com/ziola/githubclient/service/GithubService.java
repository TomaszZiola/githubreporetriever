package com.ziola.githubclient.service;

import com.ziola.githubclient.api.dto.BranchResponse;
import com.ziola.githubclient.api.dto.GithubRepositoryResponse;
import com.ziola.githubclient.client.GithubClient;
import com.ziola.githubclient.dto.Repository;
import com.ziola.githubclient.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import java.util.List;
import static java.util.stream.Collectors.toList;

@Service
public class GithubService {
    private final GithubClient githubClient;

    public GithubService(GithubClient githubClient) {
        this.githubClient = githubClient;
    }

    public List<GithubRepositoryResponse> retrieveRepositories(String username) {
        try {
            return githubClient.getRepositories(username)
                    .stream()
                    .filter(repo -> !repo.fork())
                    .map(repo -> mapToGithubRepositoryResponse(username, repo))
                    .toList();
        } catch (NotFound errorException) {
            throw new UserNotFoundException("User '" + username + "' not found");
        }
    }

    private GithubRepositoryResponse mapToGithubRepositoryResponse(String username, Repository repo) {
        List<BranchResponse> branchResponses = githubClient.getBranches(username, repo.name())
                .stream()
                .map(branch -> new BranchResponse(branch.name(), branch.commit().sha()))
                .toList();
        return new GithubRepositoryResponse(repo.name(), repo.owner().login(), branchResponses);
    }
}
