package com.smalaca.messagesender.controller;


import com.smalaca.messagesender.domain.User;
import com.smalaca.messagesender.service.Response;
import com.smalaca.messagesender.service.UserCrud;
import com.smalaca.messagesender.service.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserCrud userCrud;

    @RequestMapping("/create")
    public Response createUser(@ModelAttribute UserDto userDto) {
        return userCrud.createUser(userDto);
    }

    @RequestMapping("/block")
    public Response blockUser(@RequestParam String login) {
        return userCrud.blockUser(login);
    }

    @RequestMapping("/show/all")
    public List<User> showAllUsers() {
        return userCrud.showAllUsers();
    }

    @RequestMapping("/update")
    public Response updateUser(@ModelAttribute UserDto userDto) {
        return userCrud.updateUser(userDto);
    }

    @RequestMapping("/show/{login}")
    public List<User> showInfoAboutUser(@PathVariable String login) {
        return userCrud.showUser(login);
    }
}