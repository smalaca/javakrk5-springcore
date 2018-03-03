package com.smalaca.messagesender.rest.api;

import com.smalaca.messagesender.service.MessageCrud;
import com.smalaca.messagesender.service.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageCrudController {

    private final MessageCrud messageCrud;

    @Autowired
    public MessageCrudController(MessageCrud messageCrud) {
        this.messageCrud = messageCrud;
    }

    @RequestMapping("/message/delete")
    public Response delete(@RequestParam(name="id", defaultValue = "NO_ID") String uniqueId) {
        return messageCrud.deleteMessage(uniqueId);
    }
}
