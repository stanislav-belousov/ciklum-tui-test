package com.example.ciklumtuitest.controller;

import com.example.ciklumtuitest.dto.RepositoryInfo;
import com.example.ciklumtuitest.exception.GitHubUserNotFoundException;
import com.example.ciklumtuitest.service.GitHubRepositoriesService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@ControllerAdvice
@RequiredArgsConstructor
public class GitHubRepositoriesController {

    private final GitHubRepositoriesService gitHubRepositoriesService;

    @Operation(summary = "Get a repository information by username")
    @GetMapping(
            value = "/{userName}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RepositoryInfo> getInfoByUserName(@PathVariable(value = "userName") String userName) {
        return gitHubRepositoriesService.getRepositoriesInfoByUserName(userName);
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(GitHubUserNotFoundException.class)
    public ModelAndView handleNotFoundException(GitHubUserNotFoundException exception) {
        return buildModelAndView(NOT_FOUND.value(), exception.getMessage());
    }

    @ResponseStatus(NOT_ACCEPTABLE)
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ModelAndView handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException exception) {
        return buildModelAndView(NOT_ACCEPTABLE.value(), exception.getMessage());
    }

    private ModelAndView buildModelAndView(int status, String message) {
        final var modelAndView = new ModelAndView();
        modelAndView.setView(new MappingJackson2JsonView());
        return modelAndView
                .addObject("Message", message)
                .addObject("status", status);
    }

}
