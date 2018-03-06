package com.smalaca.messagesender.service;

import com.smalaca.messagesender.domain.Message;

public interface MailSender {

    Response sendEmailSender(Message message);
    Response isSuccessfullSended();
    Response isNotSuccessFullSended();
}
