package com.smalaca.messagesender.repository.inmemory;

import com.smalaca.messagesender.domain.User;
import com.smalaca.messagesender.domain.UserFactory;
import com.smalaca.messagesender.service.UserDto;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/repositories.xml"})
public class InMemoryUserRepositoryTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private UserFactory factory = new UserFactory();

    @Autowired
    private InMemoryUserRepository inMemoryUserRepository;

    @Test
    public void shouldNotFindUser() {
        assertFalse(inMemoryUserRepository.exists("wojciech"));
    }

    @Test
    public void shouldFindUser() {
        User user = exampleUser();

        assertTrue(inMemoryUserRepository.exists(user));
        assertTrue(inMemoryUserRepository.exists(user.getLogin()));
    }

    private User exampleUser() {
        UserDto userDto = new UserDto();
        userDto.setLogin("franek");
        userDto.setEmail("franek@gmail.com");
        return factory.createFrom(userDto);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAddTwiceTheSameUser() {
        User user = exampleUser();
        inMemoryUserRepository.add(user);
    }

    @Test
    public void shouldDeleteUser() {
        User user = exampleUser();
        assertTrue(inMemoryUserRepository.delete(user));
    }

    @Test
    public void shouldBlockUser(){
        inMemoryUserRepository.blockUser(exampleUser().getLogin());

        Assert.assertTrue(inMemoryUserRepository.isBlocked(exampleUser().getLogin()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenTryToBlockWithIllegalLogin(){
        inMemoryUserRepository.blockUser("tralalala");
    }

    @Test
    public void shouldReturnFalseWhenQueryForIsBlockedFieldOfNewUser(){
        Assert.assertFalse(inMemoryUserRepository.isBlocked(exampleUser().getLogin()));
    }
}