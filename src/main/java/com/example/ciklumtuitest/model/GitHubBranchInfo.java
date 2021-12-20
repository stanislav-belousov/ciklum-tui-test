package com.example.ciklumtuitest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GitHubBranchInfo {

    private String name;
    private GitHubCommitInfo commit;

}
