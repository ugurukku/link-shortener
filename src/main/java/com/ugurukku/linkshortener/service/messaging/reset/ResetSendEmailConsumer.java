package com.ugurukku.linkshortener.service.messaging.reset;

import com.ugurukku.linkshortener.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResetSendEmailConsumer {

    private final EmailService emailService;

    @RabbitListener(queues = "RESET_SEND_EMAIL")
    public void consume(ResetSendEmailDto resetSendEmailDto){
        emailService.sendResetEmail(resetSendEmailDto);
    }

}
