package com.example.ciklumtuitest.mapper;

import com.example.ciklumtuitest.client.model.GitHubBranch;
import com.example.ciklumtuitest.client.model.GitHubRepository;
import com.example.ciklumtuitest.client.model.GitHubRepositoryOwner;
import com.example.ciklumtuitest.dto.BranchDto;
import com.example.ciklumtuitest.dto.RepositoryDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class RepositoryMapperImplTest {

    @Mock
    private BranchMapper branchMapper;

    @InjectMocks
    private RepositoryMapperImpl target;

    @Test
    @DisplayName("When map GitHub repository entity and list of its branches " +
            "Then return expected repository DTO")
    void toDto() {
        // Given
        String repositoryName = "test-repo-name";
        String ownerLogin = "test-ownerLogin";

        GitHubRepository gitHubRepository = new GitHubRepository(
                repositoryName,
                "test-repo-fullName",
                new GitHubRepositoryOwner(ownerLogin),
                false);

        GitHubBranch firstBranch = new GitHubBranch("first", null);
        GitHubBranch secondBranch = new GitHubBranch("second", null);

        BranchDto firstBranchDto = BranchDto.builder().name("first").build();
        BranchDto secondBranchDto = BranchDto.builder().name("second").build();

        given(branchMapper.toDto(firstBranch))
                .willReturn(firstBranchDto);
        given(branchMapper.toDto(secondBranch))
                .willReturn(secondBranchDto);

        // When
        RepositoryDto result = target.toDto(
                gitHubRepository,
                List.of(
                        firstBranch,
                        secondBranch
                ));

        // Then
        assertThat(result)
                .isEqualTo(RepositoryDto.builder()
                        .repositoryName(repositoryName)
                        .ownerLogin(ownerLogin)
                        .branches(List.of(
                                firstBranchDto,
                                secondBranchDto))
                        .build());
    }

}