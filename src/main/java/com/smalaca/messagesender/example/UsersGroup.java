package com.smalaca.messagesender.example;

import javax.persistence.*;

@Entity
public class UsersGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private final String description;

    @OneToOne
    private final Location location;

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
}
