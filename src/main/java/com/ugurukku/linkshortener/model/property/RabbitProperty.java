package com.ugurukku.linkshortener.model.property;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE)
@ConfigurationProperties(prefix = "rabbit")
public class RabbitProperty {

    String topicExchangeName;
    String registerQueueName;
    String resetQueueName;

}
