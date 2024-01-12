package com.ugurukku.linkshortener.service.messaging;

import com.ugurukku.linkshortener.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterSendEmailConsumer {

    private final EmailService emailService;

    @RabbitListener(queues = "REGISTER_SEND_EMAIL")
    public void consume(RegisterSendEmailDto registerSendEmailDto){
        emailService.sendRegisterEmail(registerSendEmailDto);
    }

}
