package ru.seredavin.dbpersister.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.seredavin.dbpersister.dto.GitReposDTO;
import ru.seredavin.dbpersister.entity.GitRepos;
import ru.seredavin.dbpersister.repository.GitReposRepository;
import ru.seredavin.dbpersister.service.GitReposService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GItReposServiceImpl implements GitReposService {
    private final GitReposRepository gitReposRepository;
    private final EmitterService emitterService;

    @Override
    public List<GitReposDTO> findAll() {
        return gitReposRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public GitRepos saveFromDTO(GitReposDTO gitReposDTO) {
        GitRepos gitRepo = gitReposRepository.save(toEntity(gitReposDTO));
        emitterService.pushNotification("update");
        return gitRepo;
    }

    @Override
    public void delete(GitReposDTO gitReposDTO) {
        gitReposRepository.delete(toEntity(gitReposDTO));
    }

    private GitReposDTO toDTO(GitRepos gitRepos) {
        return new GitReposDTO(gitRepos.getId(),
                gitRepos.getName(),
                gitRepos.getUrl(),
                gitRepos.getLicense());
    }

    private GitRepos toEntity(GitReposDTO gitReposDTO) {
        return new GitRepos(gitReposDTO.id,
                gitReposDTO.name,
                gitReposDTO.url,
                gitReposDTO.license);
    }
}
