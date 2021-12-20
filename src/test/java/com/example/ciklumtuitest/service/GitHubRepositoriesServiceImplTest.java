package com.example.ciklumtuitest.service;

import com.example.ciklumtuitest.client.GitHubClient;
import com.example.ciklumtuitest.dto.BranchInfo;
import com.example.ciklumtuitest.dto.RepositoryInfo;
import com.example.ciklumtuitest.model.GitHubBranchInfo;
import com.example.ciklumtuitest.model.GitHubCommitInfo;
import com.example.ciklumtuitest.model.GitHubRepositoryInfo;
import com.example.ciklumtuitest.model.GitHubRepositoryOwner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class GitHubRepositoriesServiceImplTest {

    @Mock
    private GitHubClient gitHubClient;

    @InjectMocks
    private GitHubRepositoriesServiceImpl target;

    private final String userName = "testUser";

    @Test
    @DisplayName("When user doesn't have any repositories " +
            "Then return empty list")
    void getRepositoriesInfoByUserName_withoutRepositories() {
        // Given
        given(gitHubClient.getRepositoriesInfoByUserName(userName))
                .willReturn(Collections.emptyList());

        // When
        List<RepositoryInfo> result = target.getRepositoriesInfoByUserName(userName);

        // Then
        assertThat(result)
                .isEmpty();
    }

    @Test
    @DisplayName("When user has only forks " +
            "Then return empty list")
    void getRepositoriesInfoByUserName_withForkOnly() {
        // Given
        given(gitHubClient.getRepositoriesInfoByUserName(userName))
                .willReturn(List.of(
                        new GitHubRepositoryInfo(
                                "fork-repo-name",
                                userName + "/fork-repo-name",
                                new GitHubRepositoryOwner(userName),
                                true)
                ));

        // When
        List<RepositoryInfo> result = target.getRepositoriesInfoByUserName(userName);

        // Then
        assertThat(result)
                .isEmpty();
    }

    @Test
    @DisplayName("When user has not fork repositories " +
            "Then return info about not forks")
    void getRepositoriesInfoByUserName_withNotFork() {
        // Given
        String repositoryName = "repo-name";
        String fullRepositoryName = userName + "/" + repositoryName;
        String masterBranchName = "master";
        String masterBranchSha = "a3s4d5f6g7h8jiok";
        String testBranchName = "test";
        String testBranchSha = "4se5rd6t7fy8gu9h";

        given(gitHubClient.getRepositoriesInfoByUserName(userName))
                .willReturn(List.of(
                        new GitHubRepositoryInfo(
                                "fork-repo-name",
                                userName + "/fork-repo-name",
                                new GitHubRepositoryOwner(userName),
                                true),
                        new GitHubRepositoryInfo(
                                repositoryName,
                                fullRepositoryName,
                                new GitHubRepositoryOwner(userName),
                                false)
                ));

        given(gitHubClient.getBranchesInfoByRepositoryFullName(fullRepositoryName))
                .willReturn(List.of(
                        new GitHubBranchInfo(
                                masterBranchName,
                                new GitHubCommitInfo(masterBranchSha)
                        ),
                        new GitHubBranchInfo(
                                testBranchName,
                                new GitHubCommitInfo(testBranchSha))
                ));

        // When
        List<RepositoryInfo> result = target.getRepositoriesInfoByUserName(userName);

        // Then
        assertThat(result)
                .containsExactly(RepositoryInfo.builder()
                        .ownerLogin(userName)
                        .repositoryName(repositoryName)
                        .branches(List.of(
                                BranchInfo.builder()
                                        .name(masterBranchName)
                                        .lastCommitSha(masterBranchSha)
                                        .build(),
                                BranchInfo.builder()
                                        .name(testBranchName)
                                        .lastCommitSha(testBranchSha)
                                        .build()
                        ))
                        .build());
    }

}