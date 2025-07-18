package com.ziola.githubclient.integration;

import com.ziola.githubclient.dto.response.Branch;
import com.ziola.githubclient.dto.response.Repository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class GithubControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldReturnRepositoriesWithBranches() {
        // Given
        String username = "octocat";

        // When
        ResponseEntity<Repository[]> response = restTemplate.getForEntity(
                "/{username}", Repository[].class, username);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isNotEmpty();

        Repository[] repositories = response.getBody();

        assertNotNull(repositories);
        for (Repository repo : repositories) {
            assertThat(repo.name()).isNotBlank();
            assertThat(repo.ownerLogin()).isEqualTo(username);
            assertThat(repo.branches()).isNotNull();

            for (Branch branch : repo.branches()) {
                assertThat(branch.name()).isNotBlank();
                assertThat(branch.lastCommitSha()).matches("^[a-f0-9]{40}$");
            }
        }
    }
}
