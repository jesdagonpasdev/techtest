package com.example.atostest.services;

import com.example.atostest.entities.Spaceship;
import com.example.atostest.exceptions.SpaceshipNotFoundException;
import com.example.atostest.exceptions.SpaceshipNotNameException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SpaceshipService {

    /** CREATION & MODIFY METHODS **/
    Spaceship saveSpaceship(Spaceship spaceship) throws SpaceshipNotNameException;

    /** READ METHODS **/
    Spaceship getSpaceshipById(Long id) throws SpaceshipNotFoundException;
    Page<Spaceship> getAllSpaceships(Pageable pageable);
    List<Spaceship> getSpaceshipsByName(String name);

    /** DELETE METHODS **/
    void deleteById(Long id) throws SpaceshipNotFoundException;
}
