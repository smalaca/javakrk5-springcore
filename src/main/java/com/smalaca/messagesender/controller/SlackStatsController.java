package com.smalaca.messagesender.controller;


import com.smalaca.messagesender.domain.SlackStat;
import com.smalaca.messagesender.service.MessageDto;
import com.smalaca.messagesender.service.Response;
import com.smalaca.messagesender.service.SlackStatsCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/statistic")
@RestController
public class SlackStatsController {

    @Autowired
    private SlackStatsCrud slackStatsCrud;


    @RequestMapping("/create")
    public Response createStatistic(@ModelAttribute MessageDto messageDto) {
        return slackStatsCrud.createStatistic(messageDto);
    }

    @RequestMapping("/findAll")
    public List<SlackStat> findAll() {
        return slackStatsCrud.findAll();
    }

    @RequestMapping("/findBy/{id}")
    public SlackStat findById(@PathVariable Integer id) {
        return slackStatsCrud.findById(id);
    }

    @RequestMapping("/deleteBy/{id}")
    public Response delete(@PathVariable Integer id) {
        return slackStatsCrud.delete(id);
    }
}
