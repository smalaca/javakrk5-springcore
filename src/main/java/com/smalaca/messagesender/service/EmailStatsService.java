package com.smalaca.messagesender.service;

import com.smalaca.messagesender.domain.EmailStat;
import com.smalaca.messagesender.repository.inmemory.MessageStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EmailStatsService {

    private MessageStatsRepository messageStatsRepository;

    @Autowired
    public EmailStatsService(MessageStatsRepository messageStatsRepository) {
        this.messageStatsRepository = messageStatsRepository;
    }

    List<EmailStat> findAllBy(){
        return messageStatsRepository.findAllBy();
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

    List<EmailStat> findAllByDateAfterAndBefore(Date after, Date before){
        return messageStatsRepository.findAllByDateAfterAndDateBefore(after, before);
    }
}
