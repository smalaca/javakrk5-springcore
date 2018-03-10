package com.smalaca.messagesender.service;


import com.smalaca.messagesender.domain.SlackStat;
import com.smalaca.messagesender.exceptions.inmemory.jpa.SlackStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlackStatsCrud {

    @Autowired
    private SlackStatsRepository slackStatsRepository;


    public Response createStatistic(SlackStat slackStat) {
        return null;
    }

    public List<SlackStat> findAll() {
        return null;
    }

    public SlackStat findById(SlackStat slackStat) {
        return null;
    }

    public Response delete(Integer id) {
        return null;
    }

    public Response update(SlackStat slackStat) {
        return null;
    }


}
