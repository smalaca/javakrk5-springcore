package com.smalaca.messagesender.example;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "usersgroup")
public class UsersGroup {
    @Id
    private String id;
    private String name;
    private final String description;
    private final Location location;

    @DBRef
    private final List<User> users;

    public UsersGroup(String name, String description, Location location) {
        this(name, description, location, new ArrayList<>());
    }

    @PersistenceConstructor
    public UsersGroup(String name, String description, Location location, List<User> users) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.users = users;
    }

    @Override
    public String toString() {
        return "UsersGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void add(User user) {
        users.add(user);
    }

    public List<User> users() {
        return users;
    }

    public boolean hasSame(Location location) {
        return this.location.equals(location);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersGroup that = (UsersGroup) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (!name.equals(that.name)) return false;
        if (!description.equals(that.description)) return false;
        return location.equals(that.location);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + location.hashCode();
        return result;
    }
}
