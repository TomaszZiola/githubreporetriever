package com.ziola.githubclient.utils;

import com.ziola.githubclient.api.dto.BranchResponse;
import com.ziola.githubclient.api.dto.GithubRepositoryResponse;

import java.util.List;

import static com.ziola.githubclient.utils.TestConstants.BRANCH_NAME;
import static com.ziola.githubclient.utils.TestConstants.REPO_NAME;
import static com.ziola.githubclient.utils.TestConstants.SHA;
import static com.ziola.githubclient.utils.TestConstants.USERNAME;
import static java.util.List.of;

public final class TestDtoFactory {
    public TestDtoFactory() {
        throw new IllegalStateException("Utility class should not be instantiated");
    }

    public static List<GithubRepositoryResponse> createExpectedResponse() {
        var branch = createExpectedBranch();
        var repo = new GithubRepositoryResponse(REPO_NAME, USERNAME, of(branch));
        return of(repo);
    }

    private static BranchResponse createExpectedBranch() {
        return new BranchResponse(BRANCH_NAME, SHA);
    }
}
