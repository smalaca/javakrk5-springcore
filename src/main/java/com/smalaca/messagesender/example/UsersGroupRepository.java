package com.smalaca.messagesender.example;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsersGroupRepository extends CrudRepository<UsersGroup, Long> {

    UsersGroup findByName(String name);

    @Query("SELECT ug.description FROM UsersGroup ug WHERE ug.name = :name")
    String findDescriptionByName(@Param("name") String name);

    @Query("SELECT id FROM UsersGroup WHERE name = :name")
    Optional<String> findIdByName(@Param("name") String name);

    Optional<UsersGroup> findByDescription(String description);

    List<UsersGroup> findByNameOrDescription(String name, String description);

    long countByName(String name);

    List<UsersGroup> findFirst3ByNameContainsOrderByNameAsc(String namePart);

    List<UsersGroup> findFirst3ByNameContainsOrderByDescriptionDesc(String namePart);
}
