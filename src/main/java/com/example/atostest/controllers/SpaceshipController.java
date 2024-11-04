package com.example.atostest.controllers;

import com.example.atostest.entities.Spaceship;
import com.example.atostest.exceptions.SpaceshipNotFoundException;
import com.example.atostest.services.SpaceshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spaceships")
public class SpaceshipController {

    @Autowired
    private SpaceshipService spaceshipService;

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<Spaceship> getSpaceshipById(Long id) {
        try {
            Spaceship spaceship = spaceshipService.getSpaceshipById(id);
            return ResponseEntity.ok(spaceship);
        } catch(SpaceshipNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<Spaceship>> getAllSpaceships(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Page<Spaceship> allSpaceships = spaceshipService.getAllSpaceships(PageRequest.of(page, size));
        return ResponseEntity.ok(allSpaceships);
    }

    @GetMapping
    public ResponseEntity<List<Spaceship>> getSpaceshipByName(@RequestParam("name") String name) {
        List<Spaceship> spaceships = spaceshipService.getSpaceshipsByName(name);
        return ResponseEntity.ok(spaceships);
    }

    @PostMapping
    public ResponseEntity<Spaceship> createSpaceship(@RequestBody Spaceship spaceship) {
        return ResponseEntity.ok(spaceshipService.saveSpaceship(spaceship));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Spaceship> updateSpaceship(@PathVariable Long id, @RequestBody Spaceship spaceshipUpdated) {
        try {
            Spaceship spaceshipToUpdate = spaceshipService.getSpaceshipById(id);
            spaceshipToUpdate.setName(spaceshipUpdated.getName());
            spaceshipToUpdate.setColor(spaceshipUpdated.getColor());
            spaceshipToUpdate.setSpaceshipSize(spaceshipUpdated.getSpaceshipSize());
            spaceshipToUpdate.setVisualChannel(spaceshipUpdated.getVisualChannel());

            Spaceship spaceshipSaved = spaceshipService.saveSpaceship(spaceshipToUpdate);
            return ResponseEntity.ok(spaceshipSaved);
        } catch (SpaceshipNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpaceship(@PathVariable Long id) {
        try {
            spaceshipService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (SpaceshipNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

}
