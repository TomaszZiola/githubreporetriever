package com.ziola.githubclient.api.dto;

import java.util.List;

public record GithubRepositoryResponse(
        String repositoryName,
        String ownerLogin,
        List<BranchResponse> branches
) {}
