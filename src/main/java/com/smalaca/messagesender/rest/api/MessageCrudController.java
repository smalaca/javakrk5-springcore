package com.smalaca.messagesender.rest.api;

import com.smalaca.messagesender.domain.Message;
import com.smalaca.messagesender.service.MessageCrud;
import com.smalaca.messagesender.service.MessageDto;
import com.smalaca.messagesender.service.Response;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Response add(
            @RequestParam(name="subject", defaultValue = "NO_SUBJECT") String subject,
            @RequestParam(name="body", defaultValue = "NO_BODY") String body,
            @RequestParam(name="from", defaultValue = "NO_SENDER") String from,
            @RequestParam(name="to", defaultValue = "NO_RECIPIENT") String to) {
        MessageDto messageDto = new MessageDto();
        messageDto.setSubject(subject);
        messageDto.setBody(body);
        messageDto.setFrom(from);
        messageDto.setTo(to);
        return messageCrud.createNew(messageDto);
    }

    @RequestMapping("/message/all")
    public List<Message> getAllMessages(){
        return messageCrud.getAllMessages();
    }
}
