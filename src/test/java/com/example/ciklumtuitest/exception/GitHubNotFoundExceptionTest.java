package com.example.ciklumtuitest.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GitHubNotFoundExceptionTest {

    @Test
    @DisplayName("When create new GitHubUserNotFoundException without detailed message" +
            "Then it contains expected message")
    void noArgsConstructor_validMessage() {
        // When
        GitHubNotFoundException target = new GitHubNotFoundException();

        // Then
        assertThat(target.getMessage())
                .isEqualTo("Object not found");
    }

    @Test
    @DisplayName("When create new GitHubUserNotFoundException with detailed message" +
            "Then it contains expected message")
    void constructorWithDetailedMessage_validMessage() {
        // Given
        String detailedMessage = "User not found";

        // When
        GitHubNotFoundException target = new GitHubNotFoundException(detailedMessage);

        // Then
        assertThat(target.getMessage())
                .isEqualTo(detailedMessage);
    }

}