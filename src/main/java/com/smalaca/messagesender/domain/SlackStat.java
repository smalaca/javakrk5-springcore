package com.smalaca.messagesender.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class SlackStat {

    @Id
    @GeneratedValue
    private Integer statId;

    private String messageFrom;
    private String messageTo;
    private String messageId;
    private LocalDateTime date;


    public SlackStat() {
    }

    public SlackStat(String messageFrom, String messageTo, String messageId) {
        this.messageFrom = messageFrom;
        this.messageTo = messageTo;
        this.messageId = messageId;
        this.date = LocalDateTime.now();
    }

    public String getMessageTo() {
        return messageTo;
    }

    public String getMessageId() {
        return messageId;
    }

    public Integer getId() {
        return statId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SlackStat)) return false;
        SlackStat slackStat = (SlackStat) o;
        return Objects.equals(statId, slackStat.statId) &&
                Objects.equals(messageFrom, slackStat.messageFrom) &&
                Objects.equals(messageTo, slackStat.messageTo) &&
                Objects.equals(messageId, slackStat.messageId) &&
                Objects.equals(date, slackStat.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(statId, messageFrom, messageTo, messageId, date);
    }

    public void setFrom(String from) {
        this.messageFrom = from;
    }
}
