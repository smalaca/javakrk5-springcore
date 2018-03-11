package com.smalaca.messagesender.example;

import javax.persistence.*;

@Entity
@Table(name = "users_group")
public class UsersGroupDefinition {
    @Id @GeneratedValue private Long id;
    private String name;
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    private Country country;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersGroupDefinition that = (UsersGroupDefinition) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (!name.equals(that.name)) return false;
        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

    public boolean sameAs(UsersGroup usersGroup) {
        return name.equals(usersGroup.getName()) && description.equals(usersGroup.getDescription());
    }

    public boolean hasCountry() {
        return country != null;
    }

    public void changeCountry(Country country) {
        this.country = country;
    }
}
