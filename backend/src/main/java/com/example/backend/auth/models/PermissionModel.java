package com.example.backend.auth.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionModel {
    Long id;
    String path;
    String description;
    Boolean isActive;
    Boolean isPublicRoute;
}
