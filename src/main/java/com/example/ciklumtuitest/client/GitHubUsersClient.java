package com.example.ciklumtuitest.client;

import com.example.ciklumtuitest.client.model.GitHubRepository;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Feign client for requests GitHub users API
 */
@Component
@FeignClient(name = "github-user", url = "${client.github.url}/users")
public interface GitHubUsersClient {

    /**
     * Gets some information about user's repositories by username.
     *
     * @param userName name of the current user
     * @return list of information about GitHub repositories
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{userName}/repos")
    List<GitHubRepository> getRepositoriesInfoByUserName(@PathVariable String userName);

}
