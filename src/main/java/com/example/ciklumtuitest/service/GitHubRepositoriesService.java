package com.example.ciklumtuitest.service;

import com.example.ciklumtuitest.dto.RepositoryInfo;

import java.util.List;

public interface GitHubRepositoriesService {

    List<RepositoryInfo> getRepositoriesInfoByUserName(String userName);

}
