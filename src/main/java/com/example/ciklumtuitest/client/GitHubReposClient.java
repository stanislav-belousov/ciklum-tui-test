package com.example.ciklumtuitest.client;

import com.example.ciklumtuitest.client.model.GitHubBranch;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Feign client for requests GitHub repositories API
 */
@Component
@FeignClient(name = "github-repos", url = "${client.github.url}/repos")
public interface GitHubReposClient {

    /**
     * Gets some information about repository branches by full repository name.
     *
     * @param repositoryFullName full name of the current repository
     * @return list of information about GitHub branches
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{repositoryFullName}/branches")
    List<GitHubBranch> getBranchesInfoByRepositoryFullName(@PathVariable String repositoryFullName);

}
