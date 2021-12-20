package com.example.ciklumtuitest.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Short commit model of GitHub API
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GitHubCommit {

    /**
     * Commit SHA
     */
    private String sha;

}
