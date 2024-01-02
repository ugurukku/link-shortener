package com.ugurukku.linkshortener.service.messaging;

import com.ugurukku.linkshortener.service.rabbit.RabbitService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class SendEmailProducer {

    RabbitService<SendEmailDto> rabbitService;

    public void produce(SendEmailDto sendEmailDto){
        rabbitService.send("SEND_EMAIL",sendEmailDto);
    }

}
