package com.jdasilva.Swingy.model.enemy;

import java.util.List;

import com.jdasilva.Swingy.model.hero.Artifact;
import com.jdasilva.Swingy.model.hero.ArtifactManager;
import com.jdasilva.Swingy.model.hero.Hero;

public abstract class Enemy {
    protected String name;
    protected int attack;
    protected int hitPoints;
    protected int experience;
    protected List<Artifact> artifacts;

    public Enemy(String name, int attack, int hitPoints, int experience) {
        this.name = name;
        this.attack = attack;
        this.hitPoints = hitPoints;
        this.experience = experience;
    }

    public abstract void initializeArtifacts(Hero hero, ArtifactManager artifacts);

    public abstract Artifact getArtifact();

    public int takeDamage(int damage) {
        hitPoints -= damage;
        return hitPoints;
    }
    
    public boolean isDead() {
        return hitPoints <= 0;
    }

    public List<Artifact> ListArtifacts() {
        return artifacts;
    }

    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getExperience() {
        return experience;
    }
}
