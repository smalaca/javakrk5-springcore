package com.smalaca.messagesender.example;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class UsersGroupRepositoryTest {
    private static final Long NOT_EXISITING_ID = 13L;
    private static final String SOME_VALUE = "value";
    private static final String SOME_NAME = "name";
    private static final String DIFFERENT_NAME = "different name";
    private static final String ANOTHER_NAME = "another name";
    private static final String YET_ANOTHER_NAME = "yet another name";
    private static final String SOME_DESCRIPTION = "description";
    private static final String DIFFERENT_DESCRIPTION = "different description";
    private static final String ANOTHER_DESCRIPTION = "another description";
    private static final String YET_ANOTHER_DESCRIPTION = "yet another description";

    @Autowired private UsersGroupRepository repository;

    @Test
    public void shouldRecognizeUsersGroupDoesNotExist() {
        assertFalse(repository.exists(NOT_EXISITING_ID));
    }

    @Test
    public void shouldRecognizeUsersGroupExists() {
        UsersGroup persisted = repository.save(someUsersGroup());

        assertNotNull(persisted.getId());
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
        return usersGroup(SOME_NAME, UUID.randomUUID().toString());
    }

    @Test
    public void shouldNotFindUsersGroupByName() {
        UsersGroup result = repository.findByName(SOME_NAME);

        assertNull(result);
    }

    @Test
    public void shouldFindUsersGroupByName() {
        UsersGroup usersGroup = repository.save(someUsersGroup());

        UsersGroup result = repository.findByName(SOME_NAME);

        assertEquals(usersGroup.toString(), result.toString());
    }

    @Test
    public void shouldFindDescriptionOfTheUsersGroupByName() {
        repository.save(someUsersGroup());

        String result = repository.findDescriptionByName(SOME_NAME);

        assertEquals(SOME_DESCRIPTION, result);
    }

    @Test
    public void shouldNotFindIdOfNonExistingUsersGroup() {
        Optional<String> result = repository.findIdByName(SOME_NAME);

        assertFalse(result.isPresent());
    }

    @Test
    public void shouldFindIdOfTheUsersGroupByName() {
        UsersGroup persisted = repository.save(someUsersGroup());

        Optional<String> result = repository.findIdByName(SOME_NAME);

        assertEquals(persisted.getId(), result.get());
    }

    @Test
    public void shouldNotFindUsersGroupByDescription() {
        Optional<UsersGroup> result = repository.findByDescription(SOME_DESCRIPTION);

        assertFalse(result.isPresent());
    }

    @Test
    public void shouldFindUsersGroupByDescription() {
        UsersGroup usersGroup = repository.save(someUsersGroup());

        Optional<UsersGroup> result = repository.findByDescription(SOME_DESCRIPTION);

        assertTrue(result.isPresent());
        assertEquals(usersGroup.toString(), result.get().toString());
    }

    @Test
    public void shouldCountUsersGroupsByName() {
        repository.save(someUsersGroup());
        repository.save(usersGroupWithName(DIFFERENT_NAME));
        repository.save(someUsersGroup());
        repository.save(usersGroupWithName(ANOTHER_NAME));
        repository.save(someUsersGroup());

        long result = repository.countByName(SOME_NAME);

        assertEquals(3, result);
    }

    @Test
    public void shouldFindAllUsersGroupsByNameOrDescription() {
        UsersGroup usersGroup1 = usersGroup(SOME_VALUE, SOME_DESCRIPTION);
        UsersGroup usersGroup2 = usersGroup(SOME_NAME, SOME_DESCRIPTION);
        UsersGroup usersGroup3 = usersGroup(SOME_NAME, DIFFERENT_DESCRIPTION);
        repository.save(usersGroup1);
        repository.save(usersGroup2);
        repository.save(usersGroup(ANOTHER_NAME, SOME_VALUE));
        repository.save(usersGroup3);
        repository.save(usersGroup(DIFFERENT_NAME, DIFFERENT_DESCRIPTION));

        List<UsersGroup> result = repository.findByNameOrDescription(SOME_NAME, SOME_DESCRIPTION);

        assertEquals(3, result.size());
        assertThat(result, Matchers.contains(usersGroup1, usersGroup2, usersGroup3));
    }

    @Test
    public void shouldFindFirst3UsersGroupsByNameContainsOrderByNameAsc() {
        UsersGroup usersGroup1 = usersGroupWithName(SOME_NAME);
        UsersGroup usersGroup2 = usersGroupWithName(DIFFERENT_NAME);
        UsersGroup usersGroup3 = usersGroupWithName(ANOTHER_NAME);
        repository.save(usersGroupWithName(YET_ANOTHER_NAME));
        repository.save(usersGroup1);
        repository.save(usersGroup2);
        repository.save(usersGroupWithName(SOME_VALUE));
        repository.save(usersGroup3);

        List<UsersGroup> result = repository.findFirst3ByNameContainsOrderByNameAsc("name");

        assertEquals(3, result.size());
        assertThat(result, Matchers.contains(usersGroup3, usersGroup2, usersGroup1));
    }

    @Test
    public void shouldFindFirst3UsersGroupsByNameContainsOrderByDescriptionDesc() {
        UsersGroup usersGroup1 = usersGroup(SOME_NAME, DIFFERENT_DESCRIPTION);
        UsersGroup usersGroup2 = usersGroup(DIFFERENT_NAME, YET_ANOTHER_DESCRIPTION);
        UsersGroup usersGroup3 = usersGroup(ANOTHER_NAME, SOME_DESCRIPTION);
        repository.save(usersGroup(YET_ANOTHER_NAME, ANOTHER_DESCRIPTION));
        repository.save(usersGroup1);
        repository.save(usersGroup(SOME_VALUE, SOME_VALUE));
        repository.save(usersGroup2);
        repository.save(usersGroup3);

        List<UsersGroup> result = repository.findFirst3ByNameContainsOrderByDescriptionDesc("name");

        assertEquals(3, result.size());
        assertThat(result, Matchers.contains(usersGroup2, usersGroup1, usersGroup3));
    }

    @Test
    public void shouldCountAllMatchingCriteria() {
        repository.save(usersGroupWithName(SOME_NAME));
        repository.save(usersGroupWithName(SOME_VALUE));
        repository.save(usersGroupWithName(ANOTHER_NAME));

        long result = repository.count(UsersGroupSpecification.nameContains("%name%"));

        assertEquals(2, result);
    }

    @Test
    public void shouldFindAllMatchingCriteria() {
        UsersGroup usersGroup1 = repository.save(usersGroupWithName(SOME_NAME));
        repository.save(usersGroupWithName(SOME_VALUE));
        UsersGroup usersGroup2 = repository.save(usersGroupWithName(ANOTHER_NAME));

        List<UsersGroup> result = repository.findAll(UsersGroupSpecification.nameContains("%name%"));

        assertThat(result, Matchers.containsInAnyOrder(usersGroup1, usersGroup2));
    }

    @Test
    public void shouldFindUsersGroupByCriteria() {
        UsersGroup persisted = repository.save(usersGroupWithName(SOME_NAME));
        repository.save(usersGroupWithName(ANOTHER_NAME));

        UsersGroup result = repository.findOne(UsersGroupSpecification.nameIs(SOME_NAME));

        assertEquals(persisted, result);
    }

    private UsersGroup usersGroupWithName(String name) {
        return usersGroup(name, SOME_DESCRIPTION);
    }

    private UsersGroup someUsersGroup() {
        return usersGroup(SOME_NAME, SOME_DESCRIPTION);
    }

    private UsersGroup usersGroup(String name, String description) {
        return new UsersGroup(name, description);
    }
}
