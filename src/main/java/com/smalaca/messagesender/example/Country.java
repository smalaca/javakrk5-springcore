package com.smalaca.messagesender.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Country {
    @Id @GeneratedValue
    private Long id;
    private String name;

    private Country() {}

    public Country(String name) {
        this.name= name;
    }
}
