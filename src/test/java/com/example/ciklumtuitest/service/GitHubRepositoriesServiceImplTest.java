package com.example.ciklumtuitest.service;

import com.example.ciklumtuitest.client.GitHubReposClient;
import com.example.ciklumtuitest.client.GitHubUsersClient;
import com.example.ciklumtuitest.client.model.GitHubBranch;
import com.example.ciklumtuitest.client.model.GitHubCommit;
import com.example.ciklumtuitest.client.model.GitHubRepository;
import com.example.ciklumtuitest.client.model.GitHubRepositoryOwner;
import com.example.ciklumtuitest.dto.BranchDto;
import com.example.ciklumtuitest.dto.RepositoryDto;
import com.example.ciklumtuitest.mapper.RepositoryMapper;
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
    private GitHubUsersClient gitHubUsersClient;
    @Mock
    private GitHubReposClient gitHubReposClient;
    @Mock
    private RepositoryMapper repositoryMapper;

    @InjectMocks
    private GitHubRepositoriesServiceImpl target;

    private final String userName = "testUser";

    @Test
    @DisplayName("When user doesn't have any repositories " +
            "Then return empty list")
    void getRepositoriesInfoByUserName_withoutRepositories() {
        // Given
        given(gitHubUsersClient.getRepositoriesInfoByUserName(userName))
                .willReturn(Collections.emptyList());

        // When
        List<RepositoryDto> result = target.getRepositoriesInfoByUserName(userName);

        // Then
        assertThat(result)
                .isEmpty();
    }

    @Test
    @DisplayName("When user has only forks " +
            "Then return empty list")
    void getRepositoriesInfoByUserName_withForkOnly() {
        // Given
        given(gitHubUsersClient.getRepositoriesInfoByUserName(userName))
                .willReturn(List.of(
                        new GitHubRepository(
                                "fork-repo-name",
                                userName + "/fork-repo-name",
                                new GitHubRepositoryOwner(userName),
                                true)
                ));

        // When
        List<RepositoryDto> result = target.getRepositoriesInfoByUserName(userName);

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

        GitHubRepository notForkRepository = new GitHubRepository(
                repositoryName,
                fullRepositoryName,
                new GitHubRepositoryOwner(userName),
                false);

        List<GitHubBranch> notForkRepositoryBranches = List.of(
                new GitHubBranch(
                        masterBranchName,
                        new GitHubCommit(masterBranchSha)
                ),
                new GitHubBranch(
                        testBranchName,
                        new GitHubCommit(testBranchSha))
        );

        RepositoryDto repositoryDto = RepositoryDto.builder()
                .ownerLogin(userName)
                .repositoryName(repositoryName)
                .branches(List.of(
                        BranchDto.builder()
                                .name(masterBranchName)
                                .lastCommitSha(masterBranchSha)
                                .build(),
                        BranchDto.builder()
                                .name(testBranchName)
                                .lastCommitSha(testBranchSha)
                                .build()
                ))
                .build();

        given(gitHubUsersClient.getRepositoriesInfoByUserName(userName))
                .willReturn(List.of(
                        new GitHubRepository(
                                "fork-repo-name",
                                userName + "/fork-repo-name",
                                new GitHubRepositoryOwner(userName),
                                true),
                        notForkRepository
                ));

        given(gitHubReposClient.getBranchesInfoByRepositoryFullName(fullRepositoryName))
                .willReturn(notForkRepositoryBranches);

        given(repositoryMapper.toDto(notForkRepository, notForkRepositoryBranches))
                .willReturn(repositoryDto);

        // When
        List<RepositoryDto> result = target.getRepositoriesInfoByUserName(userName);

        // Then
        assertThat(result)
                .containsExactly(repositoryDto);
    }

}