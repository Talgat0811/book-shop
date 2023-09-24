package com.example.backend.auth.repositories;

import com.example.backend.auth.entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Long> {
    List<Route> findAllByIsActiveTrue();
}
