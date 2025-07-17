package com.ziola.githubclient.dto.github;

public record GithubRepository(String name, boolean fork, GithubOwner owner) {}
