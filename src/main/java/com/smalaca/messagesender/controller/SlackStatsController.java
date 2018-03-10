package com.smalaca.messagesender.controller;


import com.smalaca.messagesender.domain.SlackStat;
import com.smalaca.messagesender.service.Response;
import com.smalaca.messagesender.service.SlackStatsCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SlackStatsController {

    @Autowired
    private SlackStatsCrud slackStatsCrud;


    @RequestMapping("/createStatistic")
    public Response createStatistic(SlackStat slackStat)
    {
        return slackStatsCrud.createStatistic(slackStat);
    }

    @RequestMapping("/findAll")
    public List<SlackStat> findAll() {
        return slackStatsCrud.findAll();
    }
    @RequestMapping("/findById")
    public SlackStat findById(Integer id) {
        return slackStatsCrud.findById(id);
    }
    @RequestMapping("/delete")
    public Response delete(Integer id) {
        return slackStatsCrud.delete(id);
    }
    @RequestMapping("/update")
    public Response update(SlackStat slackStat) {
        return slackStatsCrud.update(slackStat);
    }


}
