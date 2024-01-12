package com.ugurukku.linkshortener.service.messaging.reset;

import com.ugurukku.linkshortener.service.rabbit.RabbitService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ResetSendEmailProducer {

    RabbitService<ResetSendEmailDto> rabbitService;

    public void produce(ResetSendEmailDto resetSendEmailDto){
        rabbitService.send("RESET_SEND_EMAIL", resetSendEmailDto);
    }

}
