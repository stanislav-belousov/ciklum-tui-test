package com.example.ciklumtuitest.mapper;

import com.example.ciklumtuitest.client.model.GitHubBranch;
import com.example.ciklumtuitest.dto.BranchDto;
import org.springframework.stereotype.Component;

/**
 * Implementation of the {@link BranchMapper}
 */
@Component
public class BranchMapperImpl implements BranchMapper {

    @Override
    public BranchDto toDto(GitHubBranch gitHubBranch) {
        return BranchDto.builder()
                .name(gitHubBranch.getName())
                .lastCommitSha(gitHubBranch.getCommit().getSha())
                .build();
    }

}
