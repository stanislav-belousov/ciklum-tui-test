package com.example.ciklumtuitest.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Short information about GitHub repository
 */
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RepositoryDto {

    /**
     * Name of the repository
     */
    private String repositoryName;

    /**
     * Login of the repository owner
     */
    private String ownerLogin;

    /**
     * List of the repository {@link BranchDto branch short information}
     */
    private List<BranchDto> branches;

}
