package com.jdasilva.Swingy.model.enemy;

import java.util.List;
import com.jdasilva.Swingy.model.hero.Artifact;
import com.jdasilva.Swingy.model.hero.ArtifactManager;
import com.jdasilva.Swingy.model.hero.Hero;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

public abstract class Enemy {
    @NotNull(message = "Name cannot be null")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    protected String name;
    @Min(value = 0, message = "Attack must be greater than 0")
    @Max(value = 200, message = "Attack must be less than 200")
    protected int attack;
    @Min(value = 0, message = "Hit points must be greater than 0")
    @Max(value = 2000, message = "Hit points must be less than 2000")
    protected int hitPoints;
    @Min(value = 0, message = "Life must be greater than 0")
    @Max(value = 2000, message = "Life must be less than 2000")
    protected int life;
    @Min(value = 0, message = "Experience must be greater than 0")
    @Max(value = 2000, message = "Experience must be less than 2000")
    protected int experience;
    protected List<Artifact> artifacts;
    @NotNull(message = "Image cannot be null")
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
