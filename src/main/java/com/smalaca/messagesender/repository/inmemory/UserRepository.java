package com.smalaca.messagesender.repository.inmemory;

import com.smalaca.messagesender.domain.User;

public interface UserRepository {

    void add(User user);

    boolean delete(User user);

    User getUserByLogin(String login);  

    boolean exists(String login);

    boolean exists(User user);
}
