package ru.seredavin.dbpersister.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.seredavin.dbpersister.entity.GitRepos;

public interface GitReposRepository extends MongoRepository<GitRepos, String> {

}
