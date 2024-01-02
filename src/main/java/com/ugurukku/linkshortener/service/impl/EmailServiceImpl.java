package com.ugurukku.linkshortener.service.impl;

import com.ugurukku.linkshortener.service.EmailService;
import com.ugurukku.linkshortener.service.messaging.SendEmailDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Locale;

import static com.ugurukku.linkshortener.model.constants.OtpConstants.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailServiceImpl implements EmailService {

    final JavaMailSender mailSender;
    final MessageSource messageSource;
    @Value("${spring.mail.username}")
    String from;

    @Override
    public void send(SendEmailDto mailMessage) {
        Locale locale = new Locale(mailMessage.getContentLanguage());
        String message = messageSource.getMessage(MESSAGE, new Object[]{mailMessage.getOtp()}, locale);
        String subject = messageSource.getMessage(SUBJECT, new Object[]{mailMessage.getOtp()}, locale);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(mailMessage.getEmail());
        simpleMailMessage.setText(message);
        simpleMailMessage.setSubject(subject);
        mailSender.send(simpleMailMessage);
    }
}
