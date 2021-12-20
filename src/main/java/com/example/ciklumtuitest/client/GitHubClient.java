package com.example.ciklumtuitest.client;

import com.example.ciklumtuitest.model.GitHubBranchInfo;
import com.example.ciklumtuitest.model.GitHubRepositoryInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Component
@FeignClient(name = "github", url = "${url}")
public interface GitHubClient {

    @RequestMapping(method = RequestMethod.GET, value = "/users/{userName}/repos")
    List<GitHubRepositoryInfo> getRepositoriesInfoByUserName(@PathVariable String userName);

    @RequestMapping(method = RequestMethod.GET, value = "/repos/{repositoryFullName}/branches")
    List<GitHubBranchInfo> getBranchesInfoByRepositoryFullName(@PathVariable String repositoryFullName);

}
