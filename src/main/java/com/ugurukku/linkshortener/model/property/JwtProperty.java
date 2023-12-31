package com.ugurukku.linkshortener.model.property;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtProperty {

    String publicKey;
    String privateKey;
    Integer validityTime;

}
