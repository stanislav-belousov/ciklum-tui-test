package com.example.ciklumtuitest.controller;

import com.example.ciklumtuitest.dto.BranchDto;
import com.example.ciklumtuitest.dto.RepositoryDto;
import com.example.ciklumtuitest.exception.GitHubNotFoundException;
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
        List<RepositoryDto> repositoryDtos = List.of(
                RepositoryDto.builder()
                        .repositoryName("Repo1")
                        .ownerLogin(userName)
                        .branches(List.of(
                                BranchDto.builder()
                                        .name("master")
                                        .lastCommitSha("sha1")
                                        .build()
                        ))
                        .build(),
                RepositoryDto.builder()
                        .repositoryName("Repo2")
                        .ownerLogin(userName)
                        .branches(List.of(
                                BranchDto.builder()
                                        .name("master")
                                        .lastCommitSha("sha2")
                                        .build(),
                                BranchDto.builder()
                                        .name("test")
                                        .lastCommitSha("sha3")
                                        .build()
                        ))
                        .build()
        );

        given(gitHubRepositoriesService.getRepositoriesInfoByUserName(userName))
                .willReturn(repositoryDtos);

        // When
        List<RepositoryDto> result = target.getInfoByUserName(userName);

        // Then
        assertThat(result)
                .containsExactlyElementsOf(repositoryDtos);
    }

}