package com.ugurukku.linkshortener.service.rabbit;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class RabbitService<T> {

    RabbitTemplate template;

    public void send(String queueName,T data){
        template.convertAndSend(queueName,data);
    }



}
