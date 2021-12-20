package com.example.ciklumtuitest.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Short repository model of GitHub API
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GitHubRepository {

    /**
     * Repository name
     */
    private String name;

    /**
     * Repository full name
     */
    @JsonProperty("full_name")
    private String fullName;

    /**
     * Repository owner
     */
    private GitHubRepositoryOwner owner;

    /**
     * Flag that mark is repository fork (if true) or not (if false)
     */
    private boolean fork;

}
