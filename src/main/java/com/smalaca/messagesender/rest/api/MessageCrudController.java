package com.smalaca.messagesender.rest.api;

import com.smalaca.messagesender.service.MessageCrud;
import com.smalaca.messagesender.service.MissingIdException;
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
    public void delete(@RequestParam(name="id") String uniqueId) {
        try {
            messageCrud.deleteMessage(uniqueId);
        } catch (MissingIdException e) {
            e.getMessage();
        }
    }

    //MissingIdException() extends RuntimeException

}
