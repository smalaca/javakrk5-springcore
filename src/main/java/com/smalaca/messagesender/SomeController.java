package com.smalaca.messagesender;

import com.smalaca.messagesender.domain.Message;
import com.smalaca.messagesender.domain.MessageFactory;
import com.smalaca.messagesender.service.MessageCrud;
import com.smalaca.messagesender.service.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Arrays.asList;

@RestController
public class SomeController {
    private final MessageCrud messageCrud;

    @Autowired
    public SomeController(MessageCrud messageCrud) {
        this.messageCrud = messageCrud;
    }

    @RequestMapping("/")
    public String index() {
        return "Hi, that's the App!";
    }

    @RequestMapping("/greetings")
    public String greetings(@RequestParam(value="name") String name) {
        return "Hello " + name;
    }

    @RequestMapping("/get-all")
    public List<Message> getAll() {
        MessageDto messageDto = new MessageDto();
        messageDto.setSubject("some subject");
        messageDto.setBody("some body");
        Message message = new MessageFactory().createFrom(messageDto);

        return asList(message);
    }

    @RequestMapping("/add")
    @ResponseBody
    public String add(@ModelAttribute("foo") Foo foo) {
        return "This is " + foo.getFoo();
    }
}
