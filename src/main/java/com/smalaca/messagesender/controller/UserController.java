package com.smalaca.messagesender.controller;


import com.smalaca.messagesender.service.UserCrud;
import com.smalaca.messagesender.service.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> blockUser(@ModelAttribute UserDto userDto) {
        if (userDto.getLogin().equals("")) {
            return new ResponseEntity<>("empty login not allowed here!", HttpStatus.BAD_REQUEST);
        }
        if (userCrud.blockUser(userDto.getLogin())) {
            return new ResponseEntity<>("User Blocked", HttpStatus.OK);
        }
        return new ResponseEntity<>("User Block Failed!", HttpStatus.OK);
    }

    @RequestMapping("/update")
    public String updateUser(@ModelAttribute UserDto userDto){
        if (userCrud.updateUser(userDto))
            return "User Updated";
        else
            return "User Update Failed!";
    }


}