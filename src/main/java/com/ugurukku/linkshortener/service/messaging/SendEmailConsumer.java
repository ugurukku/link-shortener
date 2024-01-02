package com.ugurukku.linkshortener.service.messaging;

import com.ugurukku.linkshortener.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendEmailConsumer {

    private final EmailService emailService;

    @RabbitListener(queues = "SEND_EMAIL")
    public void consume(SendEmailDto sendEmailDto){
        emailService.send(sendEmailDto);
    }

}
