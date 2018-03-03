package com.smalaca.messagesender.controller;


import com.smalaca.messagesender.service.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

   /* @Autowired
    UserCrud userCrud;*/

    @RequestMapping("/createUser")
    public String createUser(@ModelAttribute UserDto userDto) {
        //userCrud.createUser(login);
        return "valid";
    }


}