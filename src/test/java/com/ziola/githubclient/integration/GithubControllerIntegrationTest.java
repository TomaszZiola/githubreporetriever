package com.ziola.githubclient.integration;

import com.ziola.githubclient.api.dto.GithubRepositoryResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.wiremock.spring.ConfigureWireMock;
import org.wiremock.spring.EnableWireMock;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.ziola.githubclient.integration.TestData.BRANCHES_JSON;
import static com.ziola.githubclient.integration.TestData.REPOS_JSON;
import static com.ziola.githubclient.integration.TestData.REPO_NAME;
import static com.ziola.githubclient.integration.TestData.USERNAME;
import static com.ziola.githubclient.integration.TestData.createExpectedResponse;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@AutoConfigureWebTestClient
@EnableWireMock(
        @ConfigureWireMock(
                baseUrlProperties = {"github.api.url"}
        )
)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class GithubControllerIntegrationTest {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void shouldReturnRepositoriesAndBranchesForUser() {
        // given
        var expectedResponse = createExpectedResponse();

        stubFor(get(urlEqualTo("/users/" + USERNAME + "/repos"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(REPOS_JSON)));

        stubFor(get(urlEqualTo("/repos/" + USERNAME + "/" + REPO_NAME + "/branches"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(BRANCHES_JSON)));

        // when & then
        webTestClient.get()
                .uri("/github/users/" + USERNAME)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBodyList(GithubRepositoryResponse.class)
                .isEqualTo(expectedResponse);
    }
}
