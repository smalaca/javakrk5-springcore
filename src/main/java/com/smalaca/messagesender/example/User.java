package com.smalaca.messagesender.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class User {
    @Id @GeneratedValue
    private Long id;
    private final String name;

    public User(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public boolean sameAs(User user) {
        return name.equals(user.name);
    }
}
