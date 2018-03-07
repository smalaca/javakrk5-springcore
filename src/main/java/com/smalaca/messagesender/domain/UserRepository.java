package com.smalaca.messagesender.domain;

import com.smalaca.messagesender.domain.User;

import java.util.List;

public interface UserRepository<T> {

    void add(User user);

    User getUserByLogin(String login);

    boolean exists(String login);

    boolean isBlocked(String login);

    boolean blockUser(String login);

    List<T> showAllUsers();
}
