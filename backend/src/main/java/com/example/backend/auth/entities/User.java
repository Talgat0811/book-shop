package com.example.backend.auth.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "auth_users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTH_USERS_SEQ")
    @SequenceGenerator(name = "AUTH_USERS_SEQ", sequenceName = "AUTH_USER_SEQ", allocationSize = 1)
    Long id;

    @Column(name = "login", unique = true)
    String login;

    String password;

    String name;

    String lastname;

    String patronymic;

    Boolean isActive;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "role_id")
    Role role;
}
