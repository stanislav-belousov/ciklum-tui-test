package com.example.ciklumtuitest.mapper;

import com.example.ciklumtuitest.client.model.GitHubBranch;
import com.example.ciklumtuitest.client.model.GitHubCommit;
import com.example.ciklumtuitest.dto.BranchDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BranchMapperImplTest {

    private BranchMapperImpl target = new BranchMapperImpl();

    @Test
    @DisplayName("When map GitHub branch entity " +
            "Then return expected branch DTO")
    void toDto() {
        // Given
        String repositoryName = "test-name";
        String repositoryLastCommitSha = "test-sha";

        GitHubBranch gitHubBranch = new GitHubBranch(repositoryName,
                new GitHubCommit(repositoryLastCommitSha));

        // When
        BranchDto result = target.toDto(gitHubBranch);

        // Then
        assertThat(result)
                .isEqualTo(BranchDto.builder()
                        .name(repositoryName)
                        .lastCommitSha(repositoryLastCommitSha)
                        .build());
    }

}