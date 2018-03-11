package com.smalaca.messagesender.example;

import org.springframework.data.annotation.Id;

public class User {
    @Id
    private String id;
    private String name;

    private User() {}

    public User(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public boolean sameAs(User user) {
        return name.equals(user.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!id.equals(user.id)) return false;
        return name.equals(user.name);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
