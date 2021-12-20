package com.example.ciklumtuitest.controller;

import com.example.ciklumtuitest.dto.BranchInfo;
import com.example.ciklumtuitest.dto.RepositoryInfo;
import com.example.ciklumtuitest.exception.GitHubUserNotFoundException;
import com.example.ciklumtuitest.service.GitHubRepositoriesService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class GitHubRepositoriesControllerTest {

    @Mock
    private GitHubRepositoriesService gitHubRepositoriesService;

    @InjectMocks
    private GitHubRepositoriesController target;

    @Test
    @DisplayName("When gets repositories info by valid user name " +
            "Then return expected repositories ino")
    void getRepositoriesInfoByUserName() {
        // Given
        String userName = "test user name";
        List<RepositoryInfo> repositoryInfos = List.of(
                RepositoryInfo.builder()
                        .repositoryName("Repo1")
                        .ownerLogin(userName)
                        .branches(List.of(
                                BranchInfo.builder()
                                        .name("master")
                                        .lastCommitSha("sha1")
                                        .build()
                        ))
                        .build(),
                RepositoryInfo.builder()
                        .repositoryName("Repo2")
                        .ownerLogin(userName)
                        .branches(List.of(
                                BranchInfo.builder()
                                        .name("master")
                                        .lastCommitSha("sha2")
                                        .build(),
                                BranchInfo.builder()
                                        .name("test")
                                        .lastCommitSha("sha3")
                                        .build()
                        ))
                        .build()
        );

        given(gitHubRepositoriesService.getRepositoriesInfoByUserName(userName))
                .willReturn(repositoryInfos);

        // When
        List<RepositoryInfo> result = target.getInfoByUserName(userName);

        // Then
        assertThat(result)
                .containsExactlyElementsOf(repositoryInfos);
    }

    @Test
    @DisplayName("When handle http media type not acceptable exception " +
            "Than return valid model and view")
    void handleHttpMediaTypeNotAcceptable() {
        // Given
        String exceptionMessage = "Not acceptable message";
        HttpMediaTypeNotAcceptableException exception = new HttpMediaTypeNotAcceptableException(exceptionMessage);

        // When
        ModelAndView result = target.handleHttpMediaTypeNotAcceptable(exception);

        // Then
        assertModelAndView(result, 406, exceptionMessage);
    }

    @Test
    @DisplayName("When handle not found exception " +
            "Than return valid model and view")
    void handleNotFoundException() {
        // Given
        GitHubUserNotFoundException exception = new GitHubUserNotFoundException();

        // When
        ModelAndView result = target.handleNotFoundException(exception);

        // Then
        assertModelAndView(result, 404, "User name not found");
    }

    private void assertModelAndView(ModelAndView actual, int expectedStatus, String expectedMessage) {
        assertThat(actual)
                .isNotNull();

        assertThat(actual.getView())
                .isNotNull()
                .isExactlyInstanceOf(MappingJackson2JsonView.class);

        assertThat(actual.getModel())
                .containsExactlyInAnyOrderEntriesOf(Map.of(
                        "status", expectedStatus,
                        "Message", expectedMessage
                ));
    }

}