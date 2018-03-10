package com.smalaca.messagesender.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class TwitterStats {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String messageId;
    @Column(name = "sentFrom")
    private String from;
    private String to;
    private Date date;

    private TwitterStats() {
    }

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
