package com.example.ciklumtuitest.client;

import com.example.ciklumtuitest.exception.GitHubNotFoundException;
import feign.FeignException;
import feign.Request;
import feign.RequestTemplate;
import feign.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class GitHubNotFoundDecoderTest {

    private GitHubNotFoundDecoder target = new GitHubNotFoundDecoder();

    @DisplayName("When decode response with 404 (Not found) response code")
    @ParameterizedTest(name = "{index}. When {0} was used " +
            "Then return GitHubNotFoundException with message: {1}")
    @MethodSource("provideArgumentsForCustomDecodeTest")
    void decode_custom(String clientClassName, String detailedMessage) {
        // Given
        String methodKey = clientClassName.concat("#methodNmae(String)");
        Response response = buildResponse(404);

        // When
        Exception result = target.decode(methodKey, response);

        // Then
        assertThat(result)
                .isNotNull()
                .isExactlyInstanceOf(GitHubNotFoundException.class);

        assertThat(result.getMessage())
                .isEqualTo(detailedMessage);
    }

    private static Stream<Arguments> provideArgumentsForCustomDecodeTest() {
        return Stream.of(
                Arguments.of("GitHubUsersClient", "User not found"),
                Arguments.of("GitHubReposClient", "Repository not found"),
                Arguments.of("SomeAnotherClient", "Object not found")
        );
    }

    @Test
    @DisplayName("When decode response with 500 (Internal server error) response code " +
            "Then use default implementation")
    void decode_default_unexpectedStatusCode() {
        // Given
        Response response = buildResponse(500);

        // When
        Exception result = target.decode("expectedMethodKey", response);

        // Then
        assertThat(result)
                .isNotNull()
                .isExactlyInstanceOf(FeignException.InternalServerError.class);
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