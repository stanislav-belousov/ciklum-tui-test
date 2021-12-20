package com.example.ciklumtuitest.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Short repository owner model of GitHub API
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GitHubRepositoryOwner {

    /**
     * Owner login
     */
    private String login;

}
