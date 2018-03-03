package com.smalaca.messagesender.rest.api;

import com.smalaca.messagesender.service.MessageCrud;
import com.smalaca.messagesender.service.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class MessageCrudController {
    private final MessageCrud messageCrud;

    @Autowired
    public MessageCrudController(MessageCrud messageCrud) {
        this.messageCrud = messageCrud;
    }

    @RequestMapping("/add")
    public String add() {
        return "Added";
    }

    @RequestMapping("/get")
    public String get(@RequestParam String id) {
        return "Message with id: " + id;
    }

    @RequestMapping("/find/{id}")
    public String find(@PathVariable String id) {
        return "Found message with id: " + id;
    }

    @RequestMapping("/find-by/{id}")
    public MessageDto findById(@PathVariable String id) {
        MessageDto messageDto = new MessageDto();
        messageDto.setSubject("subject");
        messageDto.setBody("body");
        return messageDto;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(@RequestParam(name = "id") String uniqueIdentifier, @RequestParam(defaultValue = "NO_NAME") String name) {
        return "Hi " + name + ". Message with id: " + uniqueIdentifier + " removed.";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("message") MessageDto messageDto) {
        return "Updated with subject: " + messageDto.getSubject() + ", body: " + messageDto.getBody() + ", from: " + messageDto.getFrom() + ", to: " + messageDto.getTo();
    }
}
