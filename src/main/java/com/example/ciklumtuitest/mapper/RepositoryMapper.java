package com.example.ciklumtuitest.mapper;

import com.example.ciklumtuitest.client.model.GitHubBranch;
import com.example.ciklumtuitest.client.model.GitHubRepository;
import com.example.ciklumtuitest.dto.BranchDto;
import com.example.ciklumtuitest.dto.RepositoryDto;

import java.util.List;

/**
 * Mapper that used for converting repository entities
 */
public interface RepositoryMapper {

    /**
     * Converts {@link GitHubRepository} entity and list of its {@link GitHubBranch}'s 2
     * to {@link RepositoryDto}
     *
     * @param gitHubRepository source GitHub repository entity
     * @param gitHubBranches source list of GitHub repository branches
     * @return Repository DTO
     */
    RepositoryDto toDto(GitHubRepository gitHubRepository, List<GitHubBranch> gitHubBranches);

}
