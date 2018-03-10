package com.smalaca.messagesender.domain;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class EmailStat {

    @Id @GeneratedValue
    private String id;
    @Column(name = "sentFrom")
    private String from;
    @Column(name = "sentTo")
    private String to;
    private String subject;
    private Timestamp date;

    public EmailStat() {
    }

    public EmailStat(String from, String to, String subject, Timestamp date) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmailStat)) return false;
        EmailStat emailStat = (EmailStat) o;
        return Objects.equals(from, emailStat.from) &&
                Objects.equals(to, emailStat.to) &&
                Objects.equals(subject, emailStat.subject) &&
                Objects.equals(date, emailStat.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(from, to, subject, date);
    }
}
