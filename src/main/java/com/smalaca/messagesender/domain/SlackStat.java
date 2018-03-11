package com.smalaca.messagesender.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
public class SlackStat {

    @Id
    @GeneratedValue
    private Integer statId;
    @OneToOne
    private Message message;

    private LocalDateTime date;


    public SlackStat() {
    }

    public SlackStat(Message message) {
        this.message = message;
        this.date = LocalDateTime.now();
    }

    public Message getMessage() {
        return message;
    }

    public Integer getStatId() {
        return statId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
