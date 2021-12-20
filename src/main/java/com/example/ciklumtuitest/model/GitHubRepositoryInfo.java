package com.example.ciklumtuitest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GitHubRepositoryInfo {

    private String name;
    @JsonProperty("full_name")
    private String fullName;
    private GitHubRepositoryOwner owner;
    private boolean fork;

}
