package com.ziola.githubclient.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Branch(
        String name,
        Commit commit
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Commit(String sha) {}
}
