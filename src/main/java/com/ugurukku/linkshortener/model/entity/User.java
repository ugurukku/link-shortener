package com.ugurukku.linkshortener.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String email;
    String password;
    @JoinColumn(name = "role_id",nullable = false,referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    Role role;
    Timestamp createdAt;
    Timestamp updatedAt;
    Boolean isActive;

}
