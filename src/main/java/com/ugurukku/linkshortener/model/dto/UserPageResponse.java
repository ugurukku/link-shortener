package com.ugurukku.linkshortener.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserPageResponse {

    Integer id;
    String email;
    String role;
    Timestamp createdAt;
    Boolean isActive;

}
