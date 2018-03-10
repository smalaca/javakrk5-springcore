package com.smalaca.messagesender.exceptions.inmemory;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SenderErrorHandler {

    private SecurityResponse responseException;

    @ExceptionHandler(NoMessageException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public SecurityResponse procesValidatorError(NoMessageException ex) {

        return responseException = new SecurityResponse(ex.getErrorName());
    }
}
