package com.example.ciklumtuitest.service;

import com.example.ciklumtuitest.client.GitHubReposClient;
import com.example.ciklumtuitest.client.GitHubUsersClient;
import com.example.ciklumtuitest.client.model.GitHubRepository;
import com.example.ciklumtuitest.dto.RepositoryDto;
import com.example.ciklumtuitest.mapper.RepositoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

/**
 * Base implementation of {@link GitHubRepositoriesService}
 */
@Service
@RequiredArgsConstructor
public class GitHubRepositoriesServiceImpl implements GitHubRepositoriesService {

    private final GitHubUsersClient gitHubUsersClient;
    private final GitHubReposClient gitHubReposClient;
    private final RepositoryMapper repositoryMapper;

    @Override
    public List<RepositoryDto> getRepositoriesInfoByUserName(String userName) {
        return gitHubUsersClient.getRepositoriesInfoByUserName(userName)
                .parallelStream()
                .filter(not(GitHubRepository::isFork))
                .map(this::buildRepositoryInfo)
                .collect(Collectors.toList());
    }

    private RepositoryDto buildRepositoryInfo(GitHubRepository gitHubRepository) {
        return repositoryMapper.toDto(gitHubRepository,
                gitHubReposClient.getBranchesInfoByRepositoryFullName(gitHubRepository.getFullName()));
    }

}
