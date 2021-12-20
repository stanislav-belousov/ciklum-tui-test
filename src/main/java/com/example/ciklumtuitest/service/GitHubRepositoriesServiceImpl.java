package com.example.ciklumtuitest.service;

import com.example.ciklumtuitest.client.GitHubClient;
import com.example.ciklumtuitest.dto.BranchInfo;
import com.example.ciklumtuitest.model.GitHubBranchInfo;
import com.example.ciklumtuitest.model.GitHubRepositoryInfo;
import com.example.ciklumtuitest.dto.RepositoryInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

@Service
@RequiredArgsConstructor
public class GitHubRepositoriesServiceImpl implements GitHubRepositoriesService {

    private final GitHubClient gitHubClient;

    @Override
    public List<RepositoryInfo> getRepositoriesInfoByUserName(String userName) {
        return gitHubClient.getRepositoriesInfoByUserName(userName)
                .stream()
                .filter(not(GitHubRepositoryInfo::isFork))
                .map(this::buildRepositoryInfo)
                .collect(Collectors.toList());
    }

    private RepositoryInfo buildRepositoryInfo(GitHubRepositoryInfo gitHubRepositoryInfo) {
        List<BranchInfo> branchInfos = gitHubClient.getBranchesInfoByRepositoryFullName(gitHubRepositoryInfo.getFullName())
                .stream()
                .map(this::buildBranchInfo)
                .collect(Collectors.toList());

        return RepositoryInfo.builder()
                .repositoryName(gitHubRepositoryInfo.getName())
                .ownerLogin(gitHubRepositoryInfo.getOwner().getLogin())
                .branches(branchInfos)
                .build();
    }

    private BranchInfo buildBranchInfo(GitHubBranchInfo gitHubBranchInfo) {
        return BranchInfo.builder()
                .name(gitHubBranchInfo.getName())
                .lastCommitSha(gitHubBranchInfo.getCommit().getSha())
                .build();
    }

}
