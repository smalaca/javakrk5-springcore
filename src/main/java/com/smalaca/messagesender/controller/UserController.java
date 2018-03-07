package com.smalaca.messagesender.controller;


import com.smalaca.messagesender.service.Response;
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
    public Response createUser(@ModelAttribute UserDto userDto) {
        return userCrud.createUser(userDto);
    }

    @RequestMapping("/user/block")
    public Response blockUser(@RequestParam String login) {
        return userCrud.blockUser(login);
    }

}