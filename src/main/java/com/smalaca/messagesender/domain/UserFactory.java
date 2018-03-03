package com.smalaca.messagesender.domain;

import com.smalaca.messagesender.service.UserDto;

public class UserFactory {

    public User createFrom(UserDto userDto) {
        return aUserBuider(userDto);
    }

    private User aUserBuider(UserDto userDto) {
        return new User.UserBuilder()
                .withEmail(userDto.getEmail())
                .withLogin(userDto.getLogin())
                .withSlack(userDto.getSlack())
                .withTwitter(userDto.getTwitter())
                .build();
    }
}
