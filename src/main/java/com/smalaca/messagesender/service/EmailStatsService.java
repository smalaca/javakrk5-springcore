package com.smalaca.messagesender.service;

import com.smalaca.messagesender.domain.EmailStats;
import com.smalaca.messagesender.repository.inmemory.MessageStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EmailStatsService {

    @Autowired
    private MessageStatsRepository messageStatsRepository;

    List<EmailStats> findAllBy(){
        return messageStatsRepository.findAllBy();
    }

    List<EmailStats> findAllByFrom(String from){
        return messageStatsRepository.findAllByFrom(from);
    }

    EmailStats findById(String id){
        return messageStatsRepository.findById(id);
    }

    List<EmailStats> findAllByTo(String to){
        return messageStatsRepository.findAllByTo(to);
    }

    List<EmailStats> findAllByDateAfterAndBefore(Date after, Date before){
        return messageStatsRepository.findAllByDateAfterAndDateBefore(after, before);
    }
}
