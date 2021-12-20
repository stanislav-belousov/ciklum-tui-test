package com.example.ciklumtuitest.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GitHubUserNotFoundExceptionTest {

    @Test
    @DisplayName("When create new GitHubUserNotFoundException " +
            "Then it contains expected message")
    void validMessage() {
        // When
        GitHubUserNotFoundException target = new GitHubUserNotFoundException();

        // Then
        assertThat(target.getMessage())
                .isEqualTo("User name not found");
    }

}