package com.smalaca.messagesender.example;

public class Location {
    private String name;

    public Location(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        return name.equals(location.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
