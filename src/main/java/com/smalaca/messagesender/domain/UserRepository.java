package com.smalaca.messagesender.domain;

import com.smalaca.messagesender.domain.User;
import com.smalaca.messagesender.exceptions.inmemory.UserAlreadyExistException;
import com.smalaca.messagesender.exceptions.inmemory.UserDoesntExistException;

public interface UserRepository {

    void add(User user) throws UserAlreadyExistException;

    void delete(User user) throws UserDoesntExistException;

    User getUserByLogin(String login);

    boolean exists(String login);

    boolean exists(User user);
}
