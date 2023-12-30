package com.ugurukku.linkshortener.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements {

    Integer id;
    String email;
    String password;
    Integer roleId;
    Timestamp createdAt;
    Timestamp updatedAt;
    Boolean isActive;

}
