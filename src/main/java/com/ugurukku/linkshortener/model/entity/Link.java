package com.ugurukku.linkshortener.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Link {

    Integer id;
    String shortLink;
    String exactLink;
    Integer userId;
    Timestamp createdAt;
    Timestamp updatedAt;
    Boolean isActive;

}
