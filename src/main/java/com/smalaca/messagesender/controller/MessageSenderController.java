package com.smalaca.messagesender.controller;


import com.smalaca.messagesender.service.MessageSenderService;
import com.smalaca.messagesender.service.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public class MessageSenderController {

    @Autowired
    MessageSenderService messageSenderService;

    @RequestMapping("/send/{param}")
    public String sendMessage(@RequestParam("param") Integer id, HttpRequest httpRequest){
        Response response =  messageSenderService.sendMessageViaEmail(String.valueOf(id));

        return "sent";
    }
}
