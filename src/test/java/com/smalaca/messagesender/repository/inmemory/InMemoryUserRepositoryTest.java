package com.smalaca.messagesender.repository.inmemory;

import com.smalaca.messagesender.domain.User;
import com.smalaca.messagesender.domain.UserFactory;
import com.smalaca.messagesender.exceptions.inmemory.UserAlreadyExistException;
import com.smalaca.messagesender.service.UserDto;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/repositories.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class InMemoryUserRepositoryTest {

    @Before
    public void addUser() {
        inMemoryUserRepository.add(exampleUser());
    }

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

    @Test(expected = UserAlreadyExistException.class)
    public void shouldNotAddTwiceTheSameUser() {
        User user = exampleUser();
        inMemoryUserRepository.add(user);
    }
}