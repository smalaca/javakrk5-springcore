package com.smalaca.messagesender.controller;

import com.smalaca.messagesender.domain.Message;
import com.smalaca.messagesender.service.MessageCrud;
import com.smalaca.messagesender.service.MessageDto;
import com.smalaca.messagesender.service.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/message")
@RestController
public class MessageCrudController {

    private final MessageCrud messageCrud;

    @Autowired
    public MessageCrudController(MessageCrud messageCrud) {
        this.messageCrud = messageCrud;
    }

    @RequestMapping("/delete")
    public Response delete(@RequestParam(name = "id") String uniqueId) {
        return messageCrud.deleteMessage(uniqueId);
    }

    @RequestMapping("/add")
    public Response add(@ModelAttribute(name = "messageDto") MessageDto messageDto) {
        return messageCrud.createNew(messageDto);
    }

    @RequestMapping("/all")
    public List<Message> getAllMessages() {
        return messageCrud.getAllMessages();
    }

    @RequestMapping("/update/{id}")
    public Response updateMessage(@PathVariable String id, @ModelAttribute MessageDto messageDto) {
        return messageCrud.updateMessage(id, messageDto);
    }


}

