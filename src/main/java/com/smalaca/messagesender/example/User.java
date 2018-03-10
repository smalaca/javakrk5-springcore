package com.smalaca.messagesender.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class User {
    @Id @GeneratedValue
    private Long id;
    private String name;

    @ManyToOne
    private UsersGroup usersGroup;

    private User() {}

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
