package com.smalaca.messagesender.rest.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(MessageWithGivenIdDoesNotExistException.class)
    public ResponseEntity<String> messageWithIdDoesNotExist(MessageWithGivenIdDoesNotExistException exception) {
        return new ResponseEntity<String>("Message with id: " + exception.getId() + " does not exist.", HttpStatus.NOT_FOUND);
    }
}
