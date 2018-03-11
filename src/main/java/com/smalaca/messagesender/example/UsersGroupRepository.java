package com.smalaca.messagesender.example;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UsersGroupRepository extends CrudRepository<UsersGroup, String> {

    UsersGroup findByName(String name);

    Optional<UsersGroup> findByDescription(String description);

    List<UsersGroup> findByNameOrDescription(String name, String description);

    long countByName(String name);

    List<UsersGroup> findFirst3ByNameContainsOrderByNameAsc(String namePart);

    List<UsersGroup> findFirst3ByNameContainsOrderByDescriptionDesc(String namePart);
}
