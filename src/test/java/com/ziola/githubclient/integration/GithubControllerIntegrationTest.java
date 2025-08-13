package com.ziola.githubclient.integration;

import com.ziola.githubclient.api.dto.GithubRepositoryResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.wiremock.spring.ConfigureWireMock;
import org.wiremock.spring.EnableWireMock;

import static com.ziola.githubclient.utils.ApiPaths.getGithubUserPath;
import static com.ziola.githubclient.utils.TestConstants.USERNAME;
import static com.ziola.githubclient.utils.ApiStubs.stubExternalApis;
import static com.ziola.githubclient.utils.TestDtoFactory.createExpectedResponse;
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

        stubExternalApis();

        // when & then
        webTestClient.get()
                .uri(getGithubUserPath(USERNAME))
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBodyList(GithubRepositoryResponse.class)
                .isEqualTo(expectedResponse);
    }
}
