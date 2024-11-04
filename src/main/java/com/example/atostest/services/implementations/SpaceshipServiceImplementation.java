package com.example.atostest.services.implementations;

import com.example.atostest.entities.Spaceship;
import com.example.atostest.exceptions.SpaceshipNotFoundException;
import com.example.atostest.exceptions.SpaceshipNotNameException;
import com.example.atostest.repositories.SpaceshipRepository;
import com.example.atostest.services.SpaceshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpaceshipServiceImplementation implements SpaceshipService {

    @Autowired
    private SpaceshipRepository spaceshipRepository;

    @Override
    public Spaceship getSpaceshipById(Long id) throws SpaceshipNotFoundException {
        if (spaceshipRepository.findById(id).isPresent()) {
            return spaceshipRepository.findById(id).get();
        } else {
            throw new SpaceshipNotFoundException(id);
        }
    }

    @Override
    public Page<Spaceship> getAllSpaceships(Pageable pageable) {
        return spaceshipRepository.findAll(pageable);
    }

    @Override
    public List<Spaceship> getSpaceshipsByName(String name) {
        return spaceshipRepository.findAll().stream()
                .filter(spaceship -> spaceship.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public Spaceship saveSpaceship(Spaceship spaceship) throws SpaceshipNotNameException {
        if (spaceship.getName().isBlank()) {
            throw new SpaceshipNotNameException();
        } else {
            return spaceshipRepository.save(spaceship);
        }
    }

    @Override
    public void deleteById(Long id) throws SpaceshipNotFoundException {
        if (spaceshipRepository.findById(id).isPresent()) {
            spaceshipRepository.deleteById(id);
        } else {
            throw new SpaceshipNotFoundException(id);
        }
    }

}
