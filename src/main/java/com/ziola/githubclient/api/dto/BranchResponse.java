package com.ziola.githubclient.api.dto;

public record BranchResponse(
        String name,
        String lastCommitSha
) {}
