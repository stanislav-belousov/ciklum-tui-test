package com.example.ciklumtuitest.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RepositoryInfo {

    private String repositoryName;
    private String ownerLogin;
    private List<BranchInfo> branches;

}
