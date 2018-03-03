package com.smalaca.messagesender.repository.inmemory;

import com.smalaca.messagesender.domain.User;
import com.smalaca.messagesender.domain.UserFactory;
import com.smalaca.messagesender.service.UserDto;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/repositories.xml"})
public class InMemoryUserRepositoryTest {

    private UserFactory factory = new UserFactory();

    @Autowired
    private InMemoryUserRepository inMemoryUserRepository;

    @Test
    public void shouldNotFindUser() {
        assertFalse(inMemoryUserRepository.exists("wojciech"));
    }

    @Test
    public void shouldFindUser() {
        UserDto userDto = new UserDto();
        userDto.setLogin("franek");
        userDto.setEmail("franek@gmail.com");
        User user = factory.createFrom(userDto);
        inMemoryUserRepository.add(user);

        assertTrue(inMemoryUserRepository.exists(user));
        assertTrue(inMemoryUserRepository.exists(user.getLogin()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAddTwiceTheSameUser() {
        UserDto userDto = new UserDto();
        userDto.setLogin("franek");
        userDto.setEmail("franek@gmail.com");
        User user = factory.createFrom(userDto);
        inMemoryUserRepository.add(user);
        inMemoryUserRepository.add(user);
    }
}