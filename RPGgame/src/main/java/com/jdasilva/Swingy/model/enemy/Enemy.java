package com.jdasilva.Swingy.model.enemy;

import java.util.List;
import com.jdasilva.Swingy.model.hero.Artifact;
import com.jdasilva.Swingy.model.hero.ArtifactManager;
import com.jdasilva.Swingy.model.hero.Hero;

public abstract class Enemy {
    protected String name;
    protected int attack;
    protected int hitPoints;
    protected int life;
    protected int experience;
    protected List<Artifact> artifacts;
    protected String image;

    public Enemy(String name, int attack, int hitPoints, int experience, String image) {
        this.name = name;
        this.attack = attack;
        this.hitPoints = hitPoints;
        this.life = hitPoints;
        this.experience = experience;
        this.image = image;
    }

    public abstract void initializeArtifacts(Hero hero, ArtifactManager artifacts);

    public abstract Artifact getArtifact();

    public int takeDamage(int damage) {
        life -= damage;
        return life;
    }
    
    public boolean isDead() {
        return life <= 0;
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
    
    public int resetLife() {
        life = hitPoints;
        return life;
    }
    
    public int getLife() {
        return life;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getExperience() {
        return experience;
    }

    public String getImage() {
        return image;
    }
}
