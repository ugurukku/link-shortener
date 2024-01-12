package com.ugurukku.linkshortener.service.impl;

import com.ugurukku.linkshortener.service.EmailService;
import com.ugurukku.linkshortener.service.messaging.RegisterSendEmailDto;
import com.ugurukku.linkshortener.service.messaging.reset.ResetSendEmailDto;
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
    public void sendRegisterEmail(RegisterSendEmailDto mailMessage) {
        Locale locale = new Locale(mailMessage.getContentLanguage());
        String message = messageSource.getMessage(REGISTER_MESSAGE, new Object[]{mailMessage.getOtp()}, locale);
        String subject = messageSource.getMessage(REGISTER_SUBJECT, new Object[]{mailMessage.getOtp()}, locale);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(mailMessage.getEmail());
        simpleMailMessage.setText(message);
        simpleMailMessage.setSubject(subject);
        mailSender.send(simpleMailMessage);
    }

    @Override
    public void sendResetEmail(ResetSendEmailDto resetSendEmailDto) {
        Locale locale = new Locale(resetSendEmailDto.getContentLanguage());
        String message = messageSource.getMessage(RESET_MESSAGE, new Object[]{resetSendEmailDto.getOtp()}, locale);
        String subject = messageSource.getMessage(RESET_SUBJECT, new Object[]{resetSendEmailDto.getOtp()}, locale);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(resetSendEmailDto.getEmail());
        simpleMailMessage.setText(message);
        simpleMailMessage.setSubject(subject);
        mailSender.send(simpleMailMessage);
    }
}
