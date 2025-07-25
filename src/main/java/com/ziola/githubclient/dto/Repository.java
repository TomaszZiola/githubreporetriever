package com.ziola.githubclient.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Repository(
        String name,
        boolean fork,
        Owner owner,
        List<Branch> branches
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Owner(String login) {}
}
