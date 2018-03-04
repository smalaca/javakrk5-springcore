package com.smalaca.messagesender.controller;


import com.smalaca.messagesender.service.UserCrud;
import com.smalaca.messagesender.service.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserCrud userCrud;

    @RequestMapping("/create")
    public String createUser(@ModelAttribute UserDto userDto) {
        if (userCrud.createUser(userDto))
            return "User Created";
        else
            return "User Creation Failed!";
    }

    @RequestMapping("/block")
    public String blockUser(@RequestParam String login) {
        if (userCrud.blockUser(login)) {
            return "User Blocked";
        }
        return "User Block Failed!";
    }

    @RequestMapping("/update")
    public String updateUser(@ModelAttribute UserDto userDto){
        if (userCrud.updateUser(userDto))
            return "User Updated";
        else
            return "User Update Failed!";
    }


}