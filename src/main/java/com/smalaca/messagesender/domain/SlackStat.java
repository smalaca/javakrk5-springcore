package com.smalaca.messagesender.domain;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class SlackStat {

    @Id
    @GeneratedValue
    private Integer statId;
    @OneToOne(cascade = {CascadeType.ALL})
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SlackStat)) return false;
        SlackStat slackStat = (SlackStat) o;
        return Objects.equals(statId, slackStat.statId) &&
                Objects.equals(message, slackStat.message) &&
                Objects.equals(date, slackStat.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(statId, message, date);
    }
}
