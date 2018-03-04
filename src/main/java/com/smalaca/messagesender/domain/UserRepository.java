package com.smalaca.messagesender.domain;

import com.smalaca.messagesender.domain.User;
import com.smalaca.messagesender.service.UserDto;

public interface UserRepository {

    void add(User user);

    User getUserByLogin(String login);

    boolean exists(String login);

    boolean isBlocked(String login);

    boolean blockUser(String login);

    void updateUser(UserDto userDto);
}
