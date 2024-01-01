package com.ugurukku.linkshortener.model.dto.link;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LinkAdminResponse {

    Integer id;
    Integer userId;
    String exactLink;
    String shortLink;
    Timestamp createdAt;
    Timestamp updatedAt;
    Boolean isActive;

}
