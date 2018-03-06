package com.smalaca.messagesender.controller;

import com.smalaca.messagesender.domain.Message;
import com.smalaca.messagesender.service.MessageCrud;
import com.smalaca.messagesender.service.MessageDto;
import com.smalaca.messagesender.service.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageCrudController {

    private final MessageCrud messageCrud;

    @Autowired
    public MessageCrudController(MessageCrud messageCrud) {
        this.messageCrud = messageCrud;
    }

    @RequestMapping("/message/delete")
    public Response delete(@RequestParam(name="id") String uniqueId) {
        return messageCrud.deleteMessage(uniqueId);
    }

    @RequestMapping("/message/add")
    public Response add(@ModelAttribute(name="messageDto") MessageDto messageDto) {
        return messageCrud.createNew(messageDto);
    }

    @RequestMapping("/message/all")
    public List<Message> getAllMessages(){
        return messageCrud.getAllMessages();
    }
}
