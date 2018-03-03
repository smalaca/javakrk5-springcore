package com.smalaca.messagesender.repository.inmemory;

import com.smalaca.messagesender.domain.User;

import java.util.HashMap;
import java.util.UUID;

public class InMemoryUserRepository implements UserRepository {
    private HashMap<String, User> users = new HashMap<>();

    @Override
    public void add(User user) {
        if (!users.values().contains(user)) {
            users.put(UUID.randomUUID().toString(), user);
        } else {
            throw new IllegalArgumentException("user already in the repository!");
        }
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public User getUserByLogin(String login) {
        return null;
    }

    @Override
    public boolean exists(String login) {
        return false;
    }

    @Override
    public boolean exists(User user) {
        return true;
    }
}
