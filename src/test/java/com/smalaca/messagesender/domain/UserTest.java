package com.smalaca.messagesender.domain;

import com.smalaca.messagesender.service.UserDto;
import org.junit.Assert;
import org.junit.Test;

public class UserTest {
    @Test
    public void shouldCreateUser() {
        UserDto userDto = new UserDto();
        userDto.setLogin("login");
        userDto.setTwitter("twitter");
        userDto.setEmail("email");
        userDto.setSlack("slack");

        User user = new UserFactory().createFrom(userDto);

        Assert.assertEquals(userDto.getEmail(), user.getEmail());
        Assert.assertEquals(userDto.getLogin(), user.getLogin());
        Assert.assertEquals(userDto.getTwitter(), user.getTwitter());
        Assert.assertEquals(userDto.getSlack(), user.getSlack());

    }
}
