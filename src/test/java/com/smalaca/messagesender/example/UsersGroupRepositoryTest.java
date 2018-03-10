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
    private static final String SOME_LOCATION = "location";

    @Autowired private UsersGroupRepository usersGroupRepository;
    @Autowired private LocationRepository locationRepository;

    @Test
    public void shouldRecognizeUsersGroupDoesNotExist() {
        assertFalse(usersGroupRepository.exists(NOT_EXISITING_ID));
    }

    @Test
    public void shouldRecognizeUsersGroupExists() {
        UsersGroup persisted = usersGroupRepository.save(someUsersGroup());

        assertNotNull(persisted.getId());
        assertTrue(usersGroupRepository.exists(persisted.getId()));
    }

    @Test
    public void shouldRemoveUsersGroup() {
        UsersGroup persisted = usersGroupRepository.save(someUsersGroup());

        usersGroupRepository.delete(persisted);

        assertFalse(usersGroupRepository.exists(persisted.getId()));
    }

    @Test
    public void shouldUpdateUsersGroup() {
        UsersGroup persisted = usersGroupRepository.save(someUsersGroup());
        String usersGroupsAsString = persisted.toString();
        persisted.changeName(DIFFERENT_NAME);

        UsersGroup updated = usersGroupRepository.save(persisted);

        assertEquals(persisted.toString(), updated.toString());
        assertNotEquals(usersGroupsAsString, updated.toString());
    }

    @Test
    public void shouldReturnNoGroupsIfEmpty() {
        Iterable<UsersGroup> usersGroups = usersGroupRepository.findAll();

        assertFalse(usersGroups.iterator().hasNext());
    }

    @Test
    public void shouldReturnUsersGroups() {
        UsersGroup usersGroup1 = usersGroupRepository.save(randomUsersGroup());
        UsersGroup usersGroup2 = usersGroupRepository.save(randomUsersGroup());
        UsersGroup usersGroup3 = usersGroupRepository.save(randomUsersGroup());

        Iterator<UsersGroup> usersGroups = usersGroupRepository.findAll().iterator();

        assertTrue(CollectionUtils.contains(usersGroups, usersGroup1));
        assertTrue(CollectionUtils.contains(usersGroups, usersGroup2));
        assertTrue(CollectionUtils.contains(usersGroups, usersGroup3));
    }

    private UsersGroup randomUsersGroup() {
        return usersGroup(SOME_NAME, UUID.randomUUID().toString());
    }

    @Test
    public void shouldNotFindUsersGroupByName() {
        UsersGroup result = usersGroupRepository.findByName(SOME_NAME);

        assertNull(result);
    }

    @Test
    public void shouldFindUsersGroupByName() {
        UsersGroup usersGroup = usersGroupRepository.save(someUsersGroup());

        UsersGroup result = usersGroupRepository.findByName(SOME_NAME);

        assertEquals(usersGroup.toString(), result.toString());
    }

    @Test
    public void shouldFindDescriptionOfTheUsersGroupByName() {
        usersGroupRepository.save(someUsersGroup());

        String result = usersGroupRepository.findDescriptionByName(SOME_NAME);

        assertEquals(SOME_DESCRIPTION, result);
    }

    @Test
    public void shouldNotFindIdOfNonExistingUsersGroup() {
        Optional<String> result = usersGroupRepository.findIdByName(SOME_NAME);

        assertFalse(result.isPresent());
    }

    @Test
    public void shouldFindIdOfTheUsersGroupByName() {
        UsersGroup persisted = usersGroupRepository.save(someUsersGroup());

        Optional<String> result = usersGroupRepository.findIdByName(SOME_NAME);

        assertEquals(persisted.getId(), result.get());
    }

    @Test
    public void shouldNotFindUsersGroupByDescription() {
        Optional<UsersGroup> result = usersGroupRepository.findByDescription(SOME_DESCRIPTION);

        assertFalse(result.isPresent());
    }

    @Test
    public void shouldFindUsersGroupByDescription() {
        UsersGroup usersGroup = usersGroupRepository.save(someUsersGroup());

        Optional<UsersGroup> result = usersGroupRepository.findByDescription(SOME_DESCRIPTION);

        assertTrue(result.isPresent());
        assertEquals(usersGroup.toString(), result.get().toString());
    }

    @Test
    public void shouldCountUsersGroupsByName() {
        usersGroupRepository.save(someUsersGroup());
        usersGroupRepository.save(usersGroupWithName(DIFFERENT_NAME));
        usersGroupRepository.save(someUsersGroup());
        usersGroupRepository.save(usersGroupWithName(ANOTHER_NAME));
        usersGroupRepository.save(someUsersGroup());

        long result = usersGroupRepository.countByName(SOME_NAME);

        assertEquals(3, result);
    }

    @Test
    public void shouldFindAllUsersGroupsByNameOrDescription() {
        UsersGroup usersGroup1 = usersGroup(SOME_VALUE, SOME_DESCRIPTION);
        UsersGroup usersGroup2 = usersGroup(SOME_NAME, SOME_DESCRIPTION);
        UsersGroup usersGroup3 = usersGroup(SOME_NAME, DIFFERENT_DESCRIPTION);
        usersGroupRepository.save(usersGroup1);
        usersGroupRepository.save(usersGroup2);
        usersGroupRepository.save(usersGroup(ANOTHER_NAME, SOME_VALUE));
        usersGroupRepository.save(usersGroup3);
        usersGroupRepository.save(usersGroup(DIFFERENT_NAME, DIFFERENT_DESCRIPTION));

        List<UsersGroup> result = usersGroupRepository.findByNameOrDescription(SOME_NAME, SOME_DESCRIPTION);

        assertEquals(3, result.size());
        assertThat(result, Matchers.contains(usersGroup1, usersGroup2, usersGroup3));
    }

    @Test
    public void shouldFindFirst3UsersGroupsByNameContainsOrderByNameAsc() {
        UsersGroup usersGroup1 = usersGroupWithName(SOME_NAME);
        UsersGroup usersGroup2 = usersGroupWithName(DIFFERENT_NAME);
        UsersGroup usersGroup3 = usersGroupWithName(ANOTHER_NAME);
        usersGroupRepository.save(usersGroupWithName(YET_ANOTHER_NAME));
        usersGroupRepository.save(usersGroup1);
        usersGroupRepository.save(usersGroup2);
        usersGroupRepository.save(usersGroupWithName(SOME_VALUE));
        usersGroupRepository.save(usersGroup3);

        List<UsersGroup> result = usersGroupRepository.findFirst3ByNameContainsOrderByNameAsc("name");

        assertEquals(3, result.size());
        assertThat(result, Matchers.contains(usersGroup3, usersGroup2, usersGroup1));
    }

    @Test
    public void shouldFindFirst3UsersGroupsByNameContainsOrderByDescriptionDesc() {
        UsersGroup usersGroup1 = usersGroup(SOME_NAME, DIFFERENT_DESCRIPTION);
        UsersGroup usersGroup2 = usersGroup(DIFFERENT_NAME, YET_ANOTHER_DESCRIPTION);
        UsersGroup usersGroup3 = usersGroup(ANOTHER_NAME, SOME_DESCRIPTION);
        usersGroupRepository.save(usersGroup(YET_ANOTHER_NAME, ANOTHER_DESCRIPTION));
        usersGroupRepository.save(usersGroup1);
        usersGroupRepository.save(usersGroup(SOME_VALUE, SOME_VALUE));
        usersGroupRepository.save(usersGroup2);
        usersGroupRepository.save(usersGroup3);

        List<UsersGroup> result = usersGroupRepository.findFirst3ByNameContainsOrderByDescriptionDesc("name");

        assertEquals(3, result.size());
        assertThat(result, Matchers.contains(usersGroup2, usersGroup1, usersGroup3));
    }

    @Test
    public void shouldCountAllMatchingCriteria() {
        usersGroupRepository.save(usersGroupWithName(SOME_NAME));
        usersGroupRepository.save(usersGroupWithName(SOME_VALUE));
        usersGroupRepository.save(usersGroupWithName(ANOTHER_NAME));

        long result = usersGroupRepository.count(UsersGroupSpecification.nameContains("%name%"));

        assertEquals(2, result);
    }

    @Test
    public void shouldFindAllMatchingCriteria() {
        UsersGroup usersGroup1 = usersGroupRepository.save(usersGroupWithName(SOME_NAME));
        usersGroupRepository.save(usersGroupWithName(SOME_VALUE));
        UsersGroup usersGroup2 = usersGroupRepository.save(usersGroupWithName(ANOTHER_NAME));

        List<UsersGroup> result = usersGroupRepository.findAll(UsersGroupSpecification.nameContains("%name%"));

        assertThat(result, Matchers.containsInAnyOrder(usersGroup1, usersGroup2));
    }

    @Test
    public void shouldFindUsersGroupByCriteria() {
        UsersGroup persisted = usersGroupRepository.save(usersGroupWithName(SOME_NAME));
        usersGroupRepository.save(usersGroupWithName(ANOTHER_NAME));

        UsersGroup result = usersGroupRepository.findOne(UsersGroupSpecification.nameIs(SOME_NAME));

        assertEquals(persisted, result);
    }

    private UsersGroup usersGroupWithName(String name) {
        return usersGroup(name, SOME_DESCRIPTION);
    }

    private UsersGroup someUsersGroup() {
        return usersGroup(SOME_NAME, SOME_DESCRIPTION);
    }

    private UsersGroup usersGroup(String name, String description) {
        return new UsersGroup(name, description, someLocation());
    }

    private Location someLocation() {
        return locationRepository.save(new Location(SOME_LOCATION));
    }
}
