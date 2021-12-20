package com.example.ciklumtuitest.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Short information about GitHub branch
 */
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BranchDto {

    /**
     * Name of the branch
     */
    private String name;

    /**
     * SHA of the last commit
     */
    private String lastCommitSha;

}
