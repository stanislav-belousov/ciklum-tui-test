package com.example.ciklumtuitest.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Short branch model of GitHub API
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GitHubBranch {

    /**
     * Branch name
     */
    private String name;

    /**
     * Branch last commit
     */
    private GitHubCommit commit;

}
