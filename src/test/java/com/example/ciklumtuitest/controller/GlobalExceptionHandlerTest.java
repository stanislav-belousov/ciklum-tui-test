package com.example.ciklumtuitest.controller;

import com.example.ciklumtuitest.dto.ExceptionMessageDto;
import com.example.ciklumtuitest.exception.GitHubNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

import static org.assertj.core.api.Assertions.assertThat;

class GlobalExceptionHandlerTest {

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private GlobalExceptionHandler target = new GlobalExceptionHandler();

    @Test
    @DisplayName("When handle http media type not acceptable exception " +
            "Than return valid response body")
    void handleHttpMediaTypeNotAcceptable() throws Exception {
        // Given
        String exceptionMessage = "Not acceptable message";
        HttpMediaTypeNotAcceptableException exception = new HttpMediaTypeNotAcceptableException(exceptionMessage);

        // When
        ResponseEntity<ExceptionMessageDto> result = target.handleHttpMediaTypeNotAcceptable(exception);

        // Then
        assertThat(result)
                .isNotNull();
        assertThat(result.getStatusCodeValue())
                .isEqualTo(406);
        assertThat(result.getBody())
                .isEqualTo((new ExceptionMessageDto(406, exceptionMessage)));
    }

    @Test
    @DisplayName("When handle not found exception " +
            "Than return valid response body")
    void handleNotFoundException() {
        // Given
        GitHubNotFoundException exception = new GitHubNotFoundException("User not found");

        // When
        ExceptionMessageDto result = target.handleNotFoundException(exception);

        // Then
        assertThat(result)
                .isEqualTo(new ExceptionMessageDto(404, "User not found"));
    }

}