package com.smalaca.messagesender.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TwitterStats {

    @Id @GeneratedValue private String id;

//    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST})
//    private Message message;
    @Column(name = "sentFrom")
    private String from;
    @Column(name = "sentTo")
    private String to;
    @Column(name = "sentDate")
    private LocalDateTime date;

    public TwitterStats() {
    }

    public TwitterStats(Message message) {
//        this.message = message;
        this.from = message.getSender();
        this.to = message.getTo();
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
//        if (!message.equals(that.message)) return false;
        if (!from.equals(that.from)) return false;
        if (!to.equals(that.to)) return false;
        return date.equals(that.date);
    }
}
