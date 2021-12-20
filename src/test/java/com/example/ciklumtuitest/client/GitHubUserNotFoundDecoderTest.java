package com.example.ciklumtuitest.client;

import com.example.ciklumtuitest.exception.GitHubUserNotFoundException;
import feign.FeignException;
import feign.Request;
import feign.RequestTemplate;
import feign.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class GitHubUserNotFoundDecoderTest {

    private GitHubUserNotFoundDecoder target = new GitHubUserNotFoundDecoder();

    private String expectedMethodKey = "GitHubClient#getRepositoriesInfoByUserName(String)";

    @Test
    @DisplayName("When call expected method and response has 404 status " +
            "Then decode to expected exception")
    void decode_custom() {
        // Given
        Response response = buildResponse(404);

        // When
        Exception result = target.decode(expectedMethodKey, response);

        // Then
        assertThat(result)
                .isNotNull()
                .isExactlyInstanceOf(GitHubUserNotFoundException.class);
    }

    @Test
    @DisplayName("When call expected method and response has not 404 status " +
            "Then use default implementation")
    void decode_default_unexpectedStatusCode() {
        // Given
        Response response = buildResponse(500);

        // When
        Exception result = target.decode(expectedMethodKey, response);

        // Then
        assertThat(result)
                .isNotNull()
                .isExactlyInstanceOf(FeignException.InternalServerError.class);
    }

    @Test
    @DisplayName("When call not expected method " +
            "Then use default implementation")
    void decode_default_unexpectedMethodKey() {
        // Given
        Response response = buildResponse(404);

        // When
        Exception result = target.decode("anotherMethodKey", response);

        // Then
        assertThat(result)
                .isNotNull()
                .isExactlyInstanceOf(FeignException.NotFound.class);
    }

    private Response buildResponse(int status) {
        return Response.builder()
                .status(status)
                .request(Request.create(
                        Request.HttpMethod.GET,
                        "http://test.com",
                        Collections.emptyMap(),
                        null,
                        new RequestTemplate()
                ))
                .build();
    }

}