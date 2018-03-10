package com.smalaca.messagesender.service;

import com.smalaca.messagesender.domain.EmailStat;
import com.smalaca.messagesender.repository.inmemory.MessageStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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

    EmailStat findById(String id){
        return messageStatsRepository.findById(id);
    }

    List<EmailStat> findAllByDateAfterAndBefore(Timestamp after, Timestamp before){
        return messageStatsRepository.findAllByDateAfterAndDateBefore(after, before);
    }
}
