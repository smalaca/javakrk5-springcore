package com.smalaca.messagesender.repository.inmemory;

import com.smalaca.messagesender.domain.User;
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

    // Wydaje mi sie że ta walidacja powinna byc w serwisie
    @Override
    public void add(User user) {
        if (!users.containsKey(user.getLogin())) {
            users.put(user.getLogin(), user);
        } else {
            throw new IllegalArgumentException("user already in the repository!");
        }
    }

    @Override
    public boolean delete(User user) {
        if (users.containsKey(user.getLogin()) && user.equals(users.get(user.getLogin()))) {
            users.remove(user.getLogin());
            return true;
        }
        return false;
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
}
