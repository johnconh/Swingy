package com.jdasilva.Swingy.model.enemy;

import java.util.Random;

import com.jdasilva.Swingy.model.hero.Artifact;
import com.jdasilva.Swingy.model.hero.ArtifactManager;
import com.jdasilva.Swingy.model.hero.Hero;

public class Goblin extends Enemy {

    private Random random;

    public Goblin() {
        super("Goblin", 30, 45, 500, "/enemyImage/goblin.png");
        random = new Random();
    }

    @Override
    public void initializeArtifacts(Hero hero, ArtifactManager artifactsmanager) {
        artifacts = artifactsmanager.getArtifacts(hero.getHeroClass(), 2);
    }

    public Artifact getArtifact() {
        return artifacts.get(random.nextInt(artifacts.size()));
    }
}
