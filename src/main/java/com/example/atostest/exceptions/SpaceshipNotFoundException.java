package com.example.atostest.exceptions;

public class SpaceshipNotFoundException extends RuntimeException {
    public SpaceshipNotFoundException(Long id) {
        super("No se encuentra una Nave con el id: " + id);
    }
}
