package com.ziola.githubclient.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.UncheckedIOException;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import static com.ziola.githubclient.utils.ApiPaths.getExternalBranchesPath;
import static com.ziola.githubclient.utils.ApiPaths.getExternalReposPath;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public final class ApiStubs {
    private ApiStubs() {
        throw new IllegalStateException("Utility class should not be instantiated");
    }

    private static final String REPOS_OCTOCAT_RESPONSE_FILE = "wiremock/repos-octocat.json";
    private static final String BRANCHES_CONSORTIUM_RESPONSE_FILE = "wiremock/branches-consortium.json";

    public static String fromFile(String path) {
        try {
            var resource = new ClassPathResource(path);
            return StreamUtils.copyToString(resource.getInputStream(), UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static void stubExternalApis() {
        stubFor(get(urlEqualTo(getExternalReposPath()))
                .willReturn(aResponse()
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                        .withBody(fromFile(REPOS_OCTOCAT_RESPONSE_FILE))));

        stubFor(get(urlEqualTo(getExternalBranchesPath()))
                .willReturn(aResponse()
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                        .withBody(fromFile(BRANCHES_CONSORTIUM_RESPONSE_FILE))));
    }
}
