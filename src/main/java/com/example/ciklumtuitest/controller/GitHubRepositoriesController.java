package com.example.ciklumtuitest.controller;

import com.example.ciklumtuitest.dto.RepositoryDto;
import com.example.ciklumtuitest.service.GitHubRepositoriesService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller that process all external requests related to the GitHub repositories
 */
@RestController
@ControllerAdvice
@RequiredArgsConstructor
@RequestMapping("/repository")
public class GitHubRepositoriesController {

    private final GitHubRepositoriesService gitHubRepositoriesService;

    /**
     * Gets information about user's not fork repositories and them branches based on owner username
     *
     * @param userName repositories owner username
     * @return list of repositories info
     */
    @Operation(summary = "Get a repository information by username")
    @GetMapping(
            value = "/owner/{userName}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RepositoryDto> getInfoByUserName(@PathVariable(value = "userName") String userName) {
        return gitHubRepositoriesService.getRepositoriesInfoByUserName(userName);
    }

}
