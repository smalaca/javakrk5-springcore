package com.smalaca.messagesender.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UsersGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private final String description;

    public UsersGroup(String name, String description) {
        this.name = name;
        this.description = description;
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
}
