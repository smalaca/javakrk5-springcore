package com.smalaca.messagesender.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Stat {

    @Id
    @GeneratedValue
    private Integer statId;

    private String messageFrom;
    private String messageTo;
    private String messageId;
    private LocalDateTime date;

    public Stat(String messageFrom, String messageTo, String messageId) {
        this.messageFrom = messageFrom;
        this.messageTo = messageTo;
        this.messageId = messageId;
        this.date = LocalDateTime.now();
    }

    public Integer getId() {
        return statId;
    }
}
