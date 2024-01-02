package com.ugurukku.linkshortener.service.messaging;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SendEmailDto implements Serializable {

    String email;
    String otp;
    String contentLanguage;

}
