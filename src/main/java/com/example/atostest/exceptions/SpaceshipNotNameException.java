package com.example.atostest.exceptions;

public class SpaceshipNotNameException extends RuntimeException {
    public SpaceshipNotNameException() {
        super("El nombre no puede ser nulo o estar vacío.");
    }
}
