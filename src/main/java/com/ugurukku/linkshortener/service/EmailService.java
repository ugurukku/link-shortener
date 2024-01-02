package com.ugurukku.linkshortener.service;

import com.ugurukku.linkshortener.service.messaging.SendEmailDto;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void send(SendEmailDto mailMessage);

}
