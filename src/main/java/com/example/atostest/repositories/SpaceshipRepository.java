package com.example.atostest.repositories;

import com.example.atostest.entities.Spaceship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpaceshipRepository extends JpaRepository<Spaceship, Long> {
}
