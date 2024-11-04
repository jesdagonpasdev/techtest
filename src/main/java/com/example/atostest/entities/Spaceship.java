package com.example.atostest.entities;

import com.example.atostest.enums.SpaceshipSize;
import com.example.atostest.enums.VisualChannel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="spaceship")
@Getter
@Setter
public class Spaceship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String name;

    @Column
    private String color;

    @Column
    @Enumerated(EnumType.STRING)
    private SpaceshipSize spaceshipSize;

    @Column
    @Enumerated(EnumType.STRING)
    private VisualChannel visualChannel;

    public Spaceship(String name, String color, SpaceshipSize spaceshipSize, VisualChannel visualChannel) {
        this.name = name;
        this.color = color;
        this.spaceshipSize = spaceshipSize;
        this.visualChannel = visualChannel;
    }

    public Spaceship(String name) {
        this.name = name;
    }
}
