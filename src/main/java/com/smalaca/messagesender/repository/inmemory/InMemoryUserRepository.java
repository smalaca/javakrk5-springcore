package com.smalaca.messagesender.repository.inmemory;

import com.smalaca.messagesender.domain.User;
import com.smalaca.messagesender.domain.UserRepository;
import com.smalaca.messagesender.exceptions.inmemory.UserAlreadyExistException;
import com.smalaca.messagesender.exceptions.inmemory.UserDoesntExistException;
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
    public void delete(User user) {
        if (users.containsKey(user.getLogin()) && user.equals(users.get(user.getLogin()))) {
            users.remove(user.getLogin());
        } else
            throw new UserDoesntExistException("User doesn't exist");
    }

    @Override
    public User getUserByLogin(String login) {
        if (users.containsKey(login)) {
            return users.get(login);
        }
        throw new IllegalArgumentException("login not in the repository!");
    }

    @Override
    public boolean exists(String login) {
        return users.containsKey(login);
    }

    @Override
    public boolean exists(User user) {
        return users.containsValue(user);
    }

    @Override
    public boolean isBlocked(String login) {
        return getUserByLogin(login).isBlocked();
    }

    @Override
    public boolean blockUser(String login) {
        User user = null;
        try {
            user = getUserByLogin(login);
        } catch (IllegalArgumentException e) {
            return false;
        }
        user.blockUser();
        users.put(login, user);
        return true;
    }
}
