package com.ziola.githubclient.dto.response;

import java.util.List;

public record Repository(String name, String ownerLogin, List<Branch> branches) {}
