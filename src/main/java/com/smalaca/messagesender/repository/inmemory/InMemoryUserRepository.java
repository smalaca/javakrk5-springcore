package com.smalaca.messagesender.repository.inmemory;

import com.smalaca.messagesender.domain.User;
import com.smalaca.messagesender.domain.UserFactory;
import com.smalaca.messagesender.domain.UserRepository;
import com.smalaca.messagesender.exceptions.inmemory.UserAlreadyExistException;
import com.smalaca.messagesender.exceptions.inmemory.UserDoesntExistException;
import com.smalaca.messagesender.service.UserDto;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryUserRepository implements UserRepository {
    private Map<String, User> users;

    public InMemoryUserRepository(Map<String, User> users) {
        this.users = users;
    }

    public InMemoryUserRepository() {

        users = new HashMap<>();
    }

    @Override
    public void add(User user) {
        if (!users.containsKey(user.getLogin())) {
            users.put(user.getLogin(), user);
        } else {
            throw new UserAlreadyExistException("user already in the repository!");
        }
    }

    @Override
    public User getUserByLogin(String login) {
        if (users.containsKey(login)) {
            return users.get(login);
        }
        throw new UserDoesntExistException(login);
    }

    @Override
    public boolean exists(String login) {
        return users.containsKey(login);
    }

    @Override
    public boolean isBlocked(String login) {
        return getUserByLogin(login).isBlocked();
    }

    @Override
    public boolean blockUser(String login) {
        getUserByLogin(login).blockUser();
        return true;
    }

    @Override
    public void updateUser(User user) {
        if (users.containsKey(user.getLogin())) {
            if (user.equals(getUserByLogin(user.getLogin()))) {
                throw new UserAlreadyExistException(user.getLogin());
            }
            users.put(user.getLogin(), user);
        } else {
            throw new UserDoesntExistException("user not found!");
        }
    }
}
