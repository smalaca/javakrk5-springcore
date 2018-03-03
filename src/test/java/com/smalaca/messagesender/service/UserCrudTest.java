package com.smalaca.messagesender.service;


import com.smalaca.messagesender.repository.inmemory.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.constraints.AssertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/message-sender.xml")
public class UserCrudTest {
    @Autowired UserRepository userRepository;
    @Autowired UserCrud userCrud;

    @Test
    public void shouldCreateUserWithOnlyLogin(){
        String login = "login";

        UserDto userDto = new UserDto();

        userDto.setLogin(login);

        boolean isUserCreated = userCrud.createUser(userDto);

        Assert.assertTrue(isUserCreated);
        AssertTrue(userRepository.exists(userDto.getLogin()));
    }
}
