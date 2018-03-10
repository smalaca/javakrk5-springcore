package com.smalaca.messagesender.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class TwitterStats {

    @Id @GeneratedValue private String id;

    private String messageId;
    @Column(name = "sentFrom")
    private String from;
    private String to;
    private LocalDateTime date;

    public TwitterStats() {
    }

    public TwitterStats(String messageId, String from, String to) {
        this.messageId = messageId;
        this.from = from;
        this.to = to;
        this.date = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TwitterStats that = (TwitterStats) o;

        if (!id.equals(that.id)) return false;
        if (!messageId.equals(that.messageId)) return false;
        if (!from.equals(that.from)) return false;
        if (!to.equals(that.to)) return false;
        return date.equals(that.date);
    }
}
