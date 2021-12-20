package com.example.ciklumtuitest.mapper;

import com.example.ciklumtuitest.client.model.GitHubBranch;
import com.example.ciklumtuitest.dto.BranchDto;

/**
 * Mapper that used for converting branch entities
 */
public interface BranchMapper {

    /**
     * Converts {@link GitHubBranch} entity to {@link BranchDto}
     *
     * @param gitHubBranch source GitHub branch entity
     * @return Branch DTO
     */
    BranchDto toDto(GitHubBranch gitHubBranch);

}
