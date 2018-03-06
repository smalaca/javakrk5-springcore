package com.smalaca.messagesender.service;

import com.smalaca.messagesender.domain.Message;

public interface MailSender {

    public Response sendEmailSender(Message message);
    public Response isSuccessfullSended();
    public Response isNotSuccessFullSended();
}
