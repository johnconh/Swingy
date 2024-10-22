package com.jdasilva.Swingy.model.hero;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

public class Artifact {
    @NotNull(message = "Name cannot be null")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;
    @NotNull(message = "Type cannot be null")
    private ArtifactType type;
    @Min(value = 0, message = "Attack must be greater than 0")
    @Max(value = 200, message = "Attack must be less than 200")
    private int attack;
    @Min(value = 0, message = "Defense must be greater than 0")
    @Max(value = 200, message = "Defense must be less than 200")
    private int defense;
    @Min(value = 0, message = "Hit points must be greater than 0")
    @Max(value = 2000, message = "Hit points must be less than 2000")
    private int hitPoints;

    public Artifact(String name, ArtifactType type, int attack, int defense, int hitPoints) {
        this.name = name;
        this.type = type;
        this.attack = attack;
        this.defense = defense;
        this.hitPoints = hitPoints;
    }

    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getHitPoints() {
        return hitPoints;
    }   
    
    public ArtifactType getType() {
        return type;
    }

    @Override
    public String toString() {
        return name;
    }
}
