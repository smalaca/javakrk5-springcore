package com.smalaca.messagesender.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorHandler implements ErrorController {
    @RequestMapping("/error")
    public String error() {
        return "ooups, something went wrong...";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
