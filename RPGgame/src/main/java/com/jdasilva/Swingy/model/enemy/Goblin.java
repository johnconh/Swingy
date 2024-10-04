package com.jdasilva.Swingy.model.enemy;

import java.util.Random;

import com.jdasilva.Swingy.model.hero.Artifact;
import com.jdasilva.Swingy.model.hero.ArtifactManager;
import com.jdasilva.Swingy.model.hero.Hero;

public class Goblin extends Enemy {

    private Random random;

    public Goblin() {
        super("Goblin", 20, 20, 250);
        random = new Random();
    }

    @Override
    public void initializeArtifacts(Hero hero, ArtifactManager artifactsmanager) {
        artifacts = artifactsmanager.getArtifacts(hero.getHeroClass(), 1);
    }

    public Artifact getArtifact() {
        return artifacts.get(random.nextInt(artifacts.size()));
    }
}
