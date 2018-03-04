package com.smalaca.messagesender.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class UsersGroupRepositoryTest {
    private static final String SOME_NAME = "name";
    private static final String SOME_DESCRIPTION = "description";
    private static final String DIFFERENT_NAME = "different name";
    private static final Long NOT_EXISITING_ID = 13L;

    @Autowired private UsersGroupRepository repository;

    @Test
    public void shouldRecognizeUsersGroupDoesNotExist() {
        assertFalse(repository.exists(NOT_EXISITING_ID));
    }

    @Test
    public void shouldRecognizeUsersGroupExists() {
        UsersGroup persisted = repository.save(someUsersGroup());

        assertTrue(repository.exists(persisted.getId()));
    }

    @Test
    public void shouldRemoveUsersGroup() {
        UsersGroup persisted = repository.save(someUsersGroup());

        repository.delete(persisted);

        assertFalse(repository.exists(persisted.getId()));
    }

    @Test
    public void shouldUpdateUsersGroup() {
        UsersGroup persisted = repository.save(someUsersGroup());
        String usersGroupsAsString = persisted.toString();
        persisted.changeName(DIFFERENT_NAME);

        UsersGroup updated = repository.save(persisted);

        assertEquals(persisted.toString(), updated.toString());
        assertNotEquals(usersGroupsAsString, updated.toString());
    }

    private UsersGroup someUsersGroup() {
        return new UsersGroup(SOME_NAME, SOME_DESCRIPTION);
    }

    @Test
    public void shouldReturnNoGroupsIfEmpty() {
        Iterable<UsersGroup> usersGroups = repository.findAll();

        assertFalse(usersGroups.iterator().hasNext());
    }

    @Test
    public void shouldReturnUsersGroups() {
        UsersGroup usersGroup1 = repository.save(randomUsersGroup());
        UsersGroup usersGroup2 = repository.save(randomUsersGroup());
        UsersGroup usersGroup3 = repository.save(randomUsersGroup());

        Iterator<UsersGroup> usersGroups = repository.findAll().iterator();

        assertTrue(CollectionUtils.contains(usersGroups, usersGroup1));
        assertTrue(CollectionUtils.contains(usersGroups, usersGroup2));
        assertTrue(CollectionUtils.contains(usersGroups, usersGroup3));
    }

    private UsersGroup randomUsersGroup() {
        return new UsersGroup(SOME_NAME, UUID.randomUUID().toString());
    }
}
