package com.smalaca.messagesender.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SenderErrorHandler {

    @ExceptionHandler(NoMessageException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public SecurityResponse procesValidationError(NoMessageException ex) {

        SecurityResponse responseException = new SecurityResponse(ex.getErrorMessage());
        return responseException;
    }
}
