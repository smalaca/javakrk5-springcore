package com.smalaca.messagesender.repository.inmemory;

import com.smalaca.messagesender.domain.User;
import com.smalaca.messagesender.domain.UserFactory;
import com.smalaca.messagesender.exceptions.inmemory.UserAlreadyExistException;
import com.smalaca.messagesender.exceptions.inmemory.UserDoesntExistException;
import com.smalaca.messagesender.service.UserDto;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

public class InMemoryUserRepositoryTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private UserFactory factory = new UserFactory();

    @Before
    public void addUser() {
        inMemoryUserRepository.add(exampleUser());
    }

    private InMemoryUserRepository inMemoryUserRepository = new InMemoryUserRepository();

    @Test
    public void shouldNotFindUser() {
        assertFalse(inMemoryUserRepository.exists("wojciech"));
    }

    @Test
    public void shouldFindUser() {
        User user = exampleUser();
        assertTrue(inMemoryUserRepository.exists(user.getLogin()));
    }

    private User exampleUser() {
        UserDto userDto = new UserDto();
        userDto.setLogin("franek");
        userDto.setEmail("franek@gmail.com");
        return factory.createFrom(userDto);
    }

    @Test(expected = UserAlreadyExistException.class)
    public void shouldNotAddTwiceTheSameUser() {
        User user = exampleUser();
        inMemoryUserRepository.add(user);
    }

    @Test
    public void shouldBlockUser() {
        inMemoryUserRepository.blockUser(exampleUser().getLogin());

        Assert.assertTrue(inMemoryUserRepository.isBlocked(exampleUser().getLogin()));
    }

    @Test(expected = UserDoesntExistException.class)
    public void shouldThrowUserDoesntExistExceptionWhenTryToBlockWithIllegalLogin() {
        inMemoryUserRepository.blockUser("tralalala");
    }

    @Test
    public void shouldReturnFalseWhenQueryForIsBlockedFieldOfNewUser() {
        Assert.assertFalse(inMemoryUserRepository.isBlocked(exampleUser().getLogin()));
    }

    @Test
    public void shouldUpdateUserIfPresent() {
        UserDto userDtoUpdate = new UserDto();
        userDtoUpdate.setLogin("franek");
        userDtoUpdate.setEmail("newEmail@gmail.com");
        userDtoUpdate.setSlack("newSlack");

        inMemoryUserRepository.updateUser(userDtoUpdate);

        User updatedUser = inMemoryUserRepository.getUserByLogin("franek");

        Assert.assertEquals(updatedUser.getLogin(), userDtoUpdate.getLogin());
        Assert.assertEquals(updatedUser.getSlack(), userDtoUpdate.getSlack());
        Assert.assertEquals(updatedUser.getEmail(), userDtoUpdate.getEmail());
    }

    @Test(expected = UserDoesntExistException.class)
    public void shouldThrowUserDoesntExistExceptionWhenTryToUpdateUserWhichDoesntExist() {
        UserDto userDtoUpdate = new UserDto();
        userDtoUpdate.setLogin("franioo");
        userDtoUpdate.setEmail("newEmail@gmail.com");
        userDtoUpdate.setSlack("newSlack");

        inMemoryUserRepository.updateUser(userDtoUpdate);
    }
}