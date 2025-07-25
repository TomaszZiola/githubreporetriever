package com.ziola.githubclient.integration;

public class TestData {

    public static final String REPOS_JSON = """
            [
              {
              "id": 132935648,
                          "node_id": "MDEwOlJlcG9zaXRvcnkxMzI5MzU2NDg=",
                          "name": "boysenberry-repo-1",
                          "full_name": "octocat/boysenberry-repo-1",
                          "private": false,
                          "owner": {
                              "login": "octocat",
                              "id": 583231,
                              "node_id": "MDQ6VXNlcjU4MzIzMQ==",
                              "avatar_url": "https://avatars.githubusercontent.com/u/583231?v=4",
                              "gravatar_id": "",
                              "url": "https://api.github.com/users/octocat",
                              "html_url": "https://github.com/octocat",
                              "followers_url": "https://api.github.com/users/octocat/followers",
                              "following_url": "https://api.github.com/users/octocat/following{/other_user}",
                              "gists_url": "https://api.github.com/users/octocat/gists{/gist_id}",
                              "starred_url": "https://api.github.com/users/octocat/starred{/owner}{/repo}",
                              "subscriptions_url": "https://api.github.com/users/octocat/subscriptions",
                              "organizations_url": "https://api.github.com/users/octocat/orgs",
                              "repos_url": "https://api.github.com/users/octocat/repos",
                              "events_url": "https://api.github.com/users/octocat/events{/privacy}",
                              "received_events_url": "https://api.github.com/users/octocat/received_events",
                              "type": "User",
                              "user_view_type": "public",
                              "site_admin": false
                          },
                          "html_url": "https://github.com/octocat/boysenberry-repo-1",
                          "description": "Testing",
                          "fork": true
              },
              {
                      "id": 18221276,
                      "node_id": "MDEwOlJlcG9zaXRvcnkxODIyMTI3Ng==",
                      "name": "git-consortium",
                      "full_name": "octocat/git-consortium",
                      "private": false,
                      "owner": {
                          "login": "octocat",
                          "id": 583231,
                          "node_id": "MDQ6VXNlcjU4MzIzMQ==",
                          "avatar_url": "https://avatars.githubusercontent.com/u/583231?v=4",
                          "gravatar_id": "",
                          "url": "https://api.github.com/users/octocat",
                          "html_url": "https://github.com/octocat",
                          "followers_url": "https://api.github.com/users/octocat/followers",
                          "following_url": "https://api.github.com/users/octocat/following{/other_user}",
                          "gists_url": "https://api.github.com/users/octocat/gists{/gist_id}",
                          "starred_url": "https://api.github.com/users/octocat/starred{/owner}{/repo}",
                          "subscriptions_url": "https://api.github.com/users/octocat/subscriptions",
                          "organizations_url": "https://api.github.com/users/octocat/orgs",
                          "repos_url": "https://api.github.com/users/octocat/repos",
                          "events_url": "https://api.github.com/users/octocat/events{/privacy}",
                          "received_events_url": "https://api.github.com/users/octocat/received_events",
                          "type": "User",
                          "user_view_type": "public",
                          "site_admin": false
                      },
                      "html_url": "https://github.com/octocat/git-consortium",
                      "description": "This repo is for demonstration purposes only.",
                      "fork": false
                      }
            ]
            """;

    public static final String BRANCHES_JSON = """
            [
              {
                       "name": "master",
                       "commit": {
                           "sha": "b33a9c7c02ad93f621fa38f0e9fc9e867e12fa0e",
                           "url": "https://api.github.com/repos/octocat/git-consortium/commits/b33a9c7c02ad93f621fa38f0e9fc9e867e12fa0e"
                       },
                       "protected": false
                   }
            ]
            """;
}
