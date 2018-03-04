package com.smalaca.messagesender.controller;


import com.smalaca.messagesender.service.UserCrud;
import com.smalaca.messagesender.service.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserCrud userCrud;

    @RequestMapping("/user/create")
    public String createUser(@ModelAttribute UserDto userDto) {
        if (userCrud.createUser(userDto))
            return "User Created";
        else
            return "User Creation Failed!";
    }

    @RequestMapping("/user/block")
    public String blockUser(@RequestParam String login) {
        if (userCrud.blockUser(login)) {
            return "User Blocked";
        }
        return "User Block Failed!";
    }
}