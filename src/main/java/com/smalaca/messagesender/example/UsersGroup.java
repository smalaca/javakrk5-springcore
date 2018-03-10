package com.smalaca.messagesender.example;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UsersGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private final String description;

    @OneToOne
    private final Location location;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = User.class)
    private final List<User> users = new ArrayList<>();

    public UsersGroup(String name, String description, Location location) {
        this.name = name;
        this.description = description;
        this.location = location;
    }

    @Override
    public String toString() {
        return "UsersGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void add(User user) {
        user.assignTo(this);
        users.add(user);
    }

    public List<User> users() {
        return users;
    }
}
