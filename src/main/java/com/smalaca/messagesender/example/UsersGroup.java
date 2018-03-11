package com.smalaca.messagesender.example;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users_group")
public class UsersGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Date creationDate;

    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private Location location;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private final List<User> users = new ArrayList<>();

    private UsersGroup() {}

    public UsersGroup(String name, String description, Location location) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.creationDate = Date.valueOf(LocalDate.now());
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
        if (!creationDate.equals(that.creationDate)) return false;
        return location.equals(that.location);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + creationDate.hashCode();
        result = 31 * result + location.hashCode();
        return result;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
