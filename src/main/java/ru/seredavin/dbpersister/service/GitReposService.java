package ru.seredavin.dbpersister.service;

import ru.seredavin.dbpersister.dto.GitReposDTO;
import ru.seredavin.dbpersister.entity.GitRepos;

import java.util.List;

public interface GitReposService {

    List<GitReposDTO> findAll();

    GitRepos saveFromDTO(GitReposDTO gitReposDTO);

    void delete(GitReposDTO gitReposDTO);
}
