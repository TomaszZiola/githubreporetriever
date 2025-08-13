package com.ziola.githubclient.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ziola.githubclient.api.dto.GithubRepositoryResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.ziola.githubclient.integration.TestData.BRANCHES_JSON;
import static com.ziola.githubclient.integration.TestData.REPOS_JSON;
import static com.ziola.githubclient.integration.TestData.REPO_NAME;
import static com.ziola.githubclient.integration.TestData.USERNAME;
import static com.ziola.githubclient.integration.TestData.createExpectedResponse;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@AutoConfigureMockRestServiceServer
@SpringBootTest
class GithubControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MockRestServiceServer mockServer;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnRepositoriesAndBranchesForUser() throws Exception {
        // given
        var expectedResponse = createExpectedResponse();

        mockServer.expect(requestTo("https://api.github.com/users/" + USERNAME + "/repos"))
                .andRespond(withSuccess(REPOS_JSON, APPLICATION_JSON));

        mockServer.expect(requestTo("https://api.github.com/repos/" + USERNAME + "/" + REPO_NAME + "/branches"))
                .andRespond(withSuccess(BRANCHES_JSON, APPLICATION_JSON));

        // when
        var result = mockMvc.perform(get("/github/users/" + USERNAME)).andExpect(status().isOk()).andReturn();


        // then
        var jsonResponse = result.getResponse().getContentAsString();

        var actualResponse = objectMapper.readValue(jsonResponse, new TypeReference<List<GithubRepositoryResponse>>() {
        });

        assertThat(actualResponse).isEqualTo(expectedResponse);
    }
}
