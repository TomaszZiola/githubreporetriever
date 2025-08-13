package com.ziola.githubclient.utils;

import static com.ziola.githubclient.utils.TestConstants.REPO_NAME;
import static com.ziola.githubclient.utils.TestConstants.USERNAME;

public final class ApiPaths {
    private ApiPaths() {
        throw new IllegalStateException("Utility class should not be instantiated");
    }

    public static String getGithubUserPath(String username) {
        return "/github/users/" + username;
    }

    public static String getExternalReposPath() {
        return "/users/" + USERNAME + "/repos";
    }

    public static String getExternalBranchesPath() {
        return "/repos/" + USERNAME + "/" + REPO_NAME + "/branches";
    }
}
