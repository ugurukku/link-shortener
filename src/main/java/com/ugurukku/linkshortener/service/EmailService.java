package com.ugurukku.linkshortener.service;

import com.ugurukku.linkshortener.service.messaging.RegisterSendEmailDto;
import com.ugurukku.linkshortener.service.messaging.reset.ResetSendEmailDto;

public interface EmailService {

    void sendRegisterEmail(RegisterSendEmailDto mailMessage);

    void sendResetEmail(ResetSendEmailDto resetSendEmailDto);
}
