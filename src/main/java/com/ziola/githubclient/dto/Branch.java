package com.ziola.githubclient.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Branch(
        String name,
        Commit commit
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Commit(String sha) {}
}
