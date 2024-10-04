package com.jdasilva.Swingy.model.hero;

public class Artifact {
    private String name;
    private ArtifactType type;
    private int attack;
    private int defense;
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
}
