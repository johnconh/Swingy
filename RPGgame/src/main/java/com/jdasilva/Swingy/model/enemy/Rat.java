package com.jdasilva.Swingy.model.enemy;

import java.util.Random;
import com.jdasilva.Swingy.model.hero.Artifact;
import com.jdasilva.Swingy.model.hero.ArtifactManager;
import com.jdasilva.Swingy.model.hero.Hero;

public class Rat extends Enemy {
    private Random random;

    public Rat() {
        super("Rat", 20, 50, 250, "/enemyImage/rat.png");
        random = new Random();
    }

    @Override
    public void initializeArtifacts(Hero hero, ArtifactManager artifactsmanager) {
        artifacts = artifactsmanager.getArtifacts(hero.getHeroClass(), 1);
    }

    @Override
    public Artifact getArtifact() {
        return artifacts.get(random.nextInt(artifacts.size()));
    }   
    
}
