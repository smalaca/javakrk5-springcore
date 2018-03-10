package com.smalaca.messagesender.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id @GeneratedValue
    private Long id;
    private final String name;

    @ManyToOne
    private UsersGroup usersGroup;

    public User(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public boolean sameAs(User user) {
        return name.equals(user.name);
    }

    public UsersGroup getUsersGroup() {
        return usersGroup;
    }

    public void assignTo(UsersGroup usersGroup) {
        this.usersGroup = usersGroup;
    }
}
