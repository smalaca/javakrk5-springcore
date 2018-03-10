package com.smalaca.messagesender.service;


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


    public Response createStatistic(SlackStat slackStat) {

        if (statisticValid(slackStat)) {
            slackStatsRepository.save(slackStat);
            return Response.aSuccessfulResponseWith("Slack statistic added");
        } else {
            return Response.aFailureResponse("Cannot add statistic with empty fields");
        }
    }

    private boolean statisticValid(SlackStat slackStat) {
        if (slackStat.getMessageId() == null || slackStat.getMessageFrom() == null || slackStat.getMessageTo() == null)
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

    public Response update(SlackStat slackStat) {


        if (slackStatsRepository.exists(slackStat.getId())) {
            SlackStat statToUpdate = findById(slackStat.getId());
            statToUpdate.setFrom(slackStat.getMessageFrom());
            statToUpdate.setMessageTo(slackStat.getMessageTo());

            slackStatsRepository.save(statToUpdate);
            return Response.aSuccessfulResponseWith("Statistic update");
        } else {
            return Response.aFailureResponse("Statistic not exist");
        }
    }


}
