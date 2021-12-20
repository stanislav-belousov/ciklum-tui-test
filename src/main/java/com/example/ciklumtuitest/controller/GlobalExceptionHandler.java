package com.example.ciklumtuitest.controller;

import com.example.ciklumtuitest.dto.ExceptionMessageDto;
import com.example.ciklumtuitest.exception.GitHubNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Global exception handler for all controllers.
 * Contains all custom exception handling.
 */
@ControllerAdvice
class GlobalExceptionHandler {

    /**
     * Exception handler that handle {@link GitHubNotFoundException}
     * when user with specified username not found.
     *
     * @param exception current exception
     * @return result model with exception details
     */
    @ResponseBody
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(GitHubNotFoundException.class)
    public ExceptionMessageDto handleNotFoundException(GitHubNotFoundException exception) {
        return new ExceptionMessageDto(NOT_FOUND.value(), exception.getMessage());
    }

    /**
     * Exception handler that handle {@link HttpMediaTypeNotAcceptableException}
     * when request contains accept header with invalid unsupported type
     *
     * @param exception current exception
     * @return result model with exception details
     */
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    public ResponseEntity<ExceptionMessageDto> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException exception) {
        ExceptionMessageDto messageDto = new ExceptionMessageDto(NOT_ACCEPTABLE.value(), exception.getMessage());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(messageDto, headers, NOT_ACCEPTABLE);
    }

}
