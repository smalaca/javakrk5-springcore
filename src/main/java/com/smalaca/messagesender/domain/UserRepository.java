package com.smalaca.messagesender.domain;

import com.smalaca.messagesender.domain.User;
import com.smalaca.messagesender.service.UserDto;

import java.util.List;

public interface UserRepository<T> {

    void add(User user);

    User getUserByLogin(String login);

    boolean exists(String login);

    boolean isBlocked(String login);

    boolean blockUser(String login);

    List<T> showAllUsers();

    boolean updateUser(User user, UserDto userDto);
}
