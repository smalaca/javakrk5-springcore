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
    public static final String FORBIDDEN_LOGIN = "";

    @Autowired
    private UserCrud userCrud;

    @RequestMapping("/create")
    public ResponseEntity<String> createUser(@ModelAttribute UserDto userDto) {
        if (userCrud.createUser(userDto))
            return new ResponseEntity<>("User Created", HttpStatus.OK);
        else
            return new ResponseEntity<>("User Creation Failed!", HttpStatus.OK);
    }

    @RequestMapping("/block")
    public ResponseEntity<String> blockUser(@RequestParam(value = "login", defaultValue = FORBIDDEN_LOGIN) String login) {
        if (login.equals(FORBIDDEN_LOGIN)) {
            return new ResponseEntity<>("empty login not allowed here!", HttpStatus.BAD_REQUEST);
        }
        if (userCrud.blockUser(login)) {
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