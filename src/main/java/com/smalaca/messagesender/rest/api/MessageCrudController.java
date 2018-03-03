package com.smalaca.messagesender.rest.api;

import com.smalaca.messagesender.rest.dto.MessageSubject;
import com.sun.corba.se.spi.activation._RepositoryStub;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageCrudController {

    @RequestMapping("/add")
    public String add() {
        return "added";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam String uniqueIdentifier, @RequestParam(name = "shortname", defaultValue = "NO_NAME") String name) {
        return "message with id: " + uniqueIdentifier + " deleted. Hi " + name;
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(@ModelAttribute("subject") MessageSubject subject) {
        return "subject of the message changed into " + subject.getSubject();
    }
}
