package com.ugurukku.linkshortener.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Permission {

   Integer id;
   String permission;
   Integer roleId;

}
