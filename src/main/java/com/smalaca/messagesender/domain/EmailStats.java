package com.smalaca.messagesender.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class EmailStats{

    @Id @GeneratedValue
    private String id;
    private String from;
    private String to;
    private String subject;
    private Date date;

    public EmailStats() {
    }

}
