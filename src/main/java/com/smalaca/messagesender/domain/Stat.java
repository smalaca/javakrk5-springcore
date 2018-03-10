package com.smalaca.messagesender.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;


@Entity
public class Stat {

    @Id
    @GeneratedValue
    private Integer statId;

    private String messageFrom;
    private String messageTo;
    private String messageId;
    private LocalDateTime date;

    public Stat() {
    }

    public Stat(String messageFrom, String messageTo, String messageId) {
        this.messageFrom = messageFrom;
        this.messageTo = messageTo;
        this.messageId = messageId;
        this.date = LocalDateTime.now();
    }

    public Integer getId() {
        return statId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stat)) return false;
        Stat stat = (Stat) o;
        return Objects.equals(statId, stat.statId) &&
                Objects.equals(messageFrom, stat.messageFrom) &&
                Objects.equals(messageTo, stat.messageTo) &&
                Objects.equals(messageId, stat.messageId) &&
                Objects.equals(date, stat.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(statId, messageFrom, messageTo, messageId, date);
    }
}
