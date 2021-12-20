package com.example.ciklumtuitest.service;

import com.example.ciklumtuitest.dto.RepositoryDto;

import java.util.List;

/**
 * Service that gets information about GitHub repositories
 */
public interface GitHubRepositoriesService {

    /**
     * Gets information about user's repositories based on username
     *
     * @param userName name of the user
     * @return list of repositories information
     */
    List<RepositoryDto> getRepositoriesInfoByUserName(String userName);

}
