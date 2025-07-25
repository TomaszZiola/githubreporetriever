package com.ziola.githubclient.integration;

import com.ziola.githubclient.GithubClientApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = GithubClientApplication.class)
@AutoConfigureMockMvc
@AutoConfigureMockRestServiceServer
class GithubControllerIntegrationTest extends TestData {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MockRestServiceServer mockServer;

    @Test
    void shouldReturnRepositoriesAndBranchesForUser() throws Exception {
        // given
        String username = "octocat";
        String repoName = "git-consortium";
        String branchName = "master";
        String sha = "b33a9c7c02ad93f621fa38f0e9fc9e867e12fa0e";

        mockServer.expect(requestTo("https://api.github.com/users/" + username + "/repos"))
                .andRespond(withSuccess(REPOS_JSON, APPLICATION_JSON));

        mockServer.expect(requestTo("https://api.github.com/repos/" + username + "/" + repoName + "/branches"))
                .andRespond(withSuccess(BRANCHES_JSON, APPLICATION_JSON));

        // when & then
        mockMvc.perform(get("/{username}", username))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].repositoryName").value(repoName))
                .andExpect(jsonPath("$[0].ownerLogin").value(username))
                .andExpect(jsonPath("$[0].branches.length()").value(1))
                .andExpect(jsonPath("$[0].branches[0].name").value(branchName))
                .andExpect(jsonPath("$[0].branches[0].lastCommitSha").value(sha));
    }
}
