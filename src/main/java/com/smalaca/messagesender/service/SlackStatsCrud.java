package com.smalaca.messagesender.service;


import com.smalaca.messagesender.domain.MessageFactory;
import com.smalaca.messagesender.domain.SlackStat;
import com.smalaca.messagesender.repository.inmemory.jpa.SlackStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SlackStatsCrud {


    @Autowired
    private SlackStatsRepository slackStatsRepository;


    public Response createStatistic(MessageDto messageDto) {
        SlackStat slackStat = new SlackStat(new MessageFactory().createWithoutId(messageDto));
        if (statisticValid(slackStat)) {
            slackStatsRepository.save(slackStat);
            return Response.aSuccessfulResponseWith("Slack statistic added");
        } else {
            return Response.aFailureResponse("Cannot add statistic with empty fields");
        }
    }

    private boolean statisticValid(SlackStat slackStat) {
        if (slackStat.getMessage().getId() == null || slackStat.getMessage().getId() == null || slackStat.getMessage().getId() == null)
            return false;
        return true;
    }

    public List<SlackStat> findAll() {
        Iterable<SlackStat> allStats = slackStatsRepository.findAll();
        List<SlackStat> lisOfAllSlackStats = new ArrayList<>();
        allStats.forEach(lisOfAllSlackStats::add);

        return lisOfAllSlackStats;
    }

    public SlackStat findById(Integer slackStatId) {
        return slackStatsRepository.findOne(slackStatId);
    }

    public Response delete(Integer id) {
        if (slackStatsRepository.exists(id)) {
            slackStatsRepository.delete(id);
            return Response.aSuccessfulResponseWith("Statistic delete");
        } else {
            return Response.aFailureResponse("Statistic not exist");
        }
    }
}
