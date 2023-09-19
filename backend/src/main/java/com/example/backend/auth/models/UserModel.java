package com.example.backend.auth.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserModel {
    Long id;
    String login;
    String password;
    String name;
    String lastname;
    String patronymic;
    Boolean isActive;
    RoleModel roleModel;
}
