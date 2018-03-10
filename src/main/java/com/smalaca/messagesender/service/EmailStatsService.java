package com.smalaca.messagesender.service;

import com.smalaca.messagesender.domain.EmailStat;
import com.smalaca.messagesender.repository.inmemory.MessageStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class EmailStatsService {

    private MessageStatsRepository messageStatsRepository;

    @Autowired
    public EmailStatsService(MessageStatsRepository messageStatsRepository) {
        this.messageStatsRepository = messageStatsRepository;
    }

    List<EmailStat> findAll(){
        return messageStatsRepository.findAll();
    }

    List<EmailStat> findAllByFrom(String from){
        return messageStatsRepository.findAllByFrom(from);
    }

    EmailStat findById(String id){
        return messageStatsRepository.findById(id);
    }

    List<EmailStat> findAllByTo(String to){
        return messageStatsRepository.findAllByTo(to);
    }

    List<EmailStat> findAllByDateAfterAndBefore(Timestamp after, Timestamp before){
        return messageStatsRepository.findAllByDateAfterAndDateBefore(after, before);
    }
}
