package com.smalaca.messagesender.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class TwitterStats {

    @Id @GeneratedValue private String id;

    private final String messageId;
    private final String from;
    private final String to;
    private final Date date;

    public TwitterStats(String messageId, String from, String to) {
        this.messageId = messageId;
        this.from = from;
        this.to = to;
        this.date = new Date();
    }

    public String getId() {
        return id;
    }
}
