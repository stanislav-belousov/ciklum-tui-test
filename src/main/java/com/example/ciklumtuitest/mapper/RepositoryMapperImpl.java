package com.example.ciklumtuitest.mapper;

import com.example.ciklumtuitest.client.model.GitHubBranch;
import com.example.ciklumtuitest.client.model.GitHubRepository;
import com.example.ciklumtuitest.dto.BranchDto;
import com.example.ciklumtuitest.dto.RepositoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link RepositoryMapper}
 */
@Component
@RequiredArgsConstructor
public class RepositoryMapperImpl implements RepositoryMapper {

    private final BranchMapper branchMapper;

    public RepositoryDto toDto(GitHubRepository gitHubRepository, List<GitHubBranch> gitHubBranches) {
        List<BranchDto> branchDtos = gitHubBranches
                .stream()
                .map(branchMapper::toDto)
                .collect(Collectors.toList());

        return RepositoryDto.builder()
                .repositoryName(gitHubRepository.getName())
                .ownerLogin(gitHubRepository.getOwner().getLogin())
                .branches(branchDtos)
                .build();
    }

}
