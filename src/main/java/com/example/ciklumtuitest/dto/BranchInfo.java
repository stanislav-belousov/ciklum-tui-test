package com.example.ciklumtuitest.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BranchInfo {

    private String name;
    private String lastCommitSha;

}
