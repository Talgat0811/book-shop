package com.example.book.auth.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "auth_permissions")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTH_PERMISSIONS_SEQ")
    @SequenceGenerator(name = "AUTH_PERMISSIONS_SEQ", sequenceName = "AUTH_PERMISSIONS_SEQ", allocationSize = 1)
    Long id;

    String path;

    @Column(name = "description", nullable = false)
    String description;

    boolean isActive;

    Boolean isPublicRoute;
}
