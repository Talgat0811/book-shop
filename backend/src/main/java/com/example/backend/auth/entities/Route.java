package com.example.backend.auth.entities;

import com.example.commons.enums.HttpMethod;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "auth_routes")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTH_ROUTES_SEQ")
    @SequenceGenerator(name = "AUTH_ROUTES_SEQ", sequenceName = "AUTH_ROUTES_SEQ", allocationSize = 1)
    Long id;

    @Column(name = "is_active",columnDefinition = "BOOLEAN DEFAULT TRUE")
    Boolean isActive;

    @Column(name = "is_public",columnDefinition = "BOOLEAN DEFAULT FALSE")
    Boolean isPublic;

    @Enumerated(EnumType.STRING)
    @Column(name = "http_method", nullable = false)
    HttpMethod httpMethod;

    @Column(name = "uri", nullable = false)
    String uri;

    String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "auth_routes_permissions",
            joinColumns = @JoinColumn(name = "auth_route_id"),
            inverseJoinColumns = @JoinColumn(name = "auth_permission_id"))
    Set<Permission> permissions = new HashSet<>();
}
