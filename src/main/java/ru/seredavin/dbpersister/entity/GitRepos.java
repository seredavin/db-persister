package ru.seredavin.dbpersister.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class GitRepos {
    @Id
    private String id;
    private String name;
    private String url;
    private String license;
}
