package com.example.atostest.enums;

public enum SpaceshipSize {
    SMALL(1),
    MEDIUM(2),
    BIG(3),
    ULTRABIG(4);

    private final long spaceshipSize;

    SpaceshipSize(long spaceshipSize) {
        this.spaceshipSize = spaceshipSize;
    }

    public long getSpaceshipSize() {
        return spaceshipSize;
    }
}
