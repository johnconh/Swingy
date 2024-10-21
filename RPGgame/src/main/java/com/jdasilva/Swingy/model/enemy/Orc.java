package com.jdasilva.Swingy.model.enemy;

import java.util.Random;

import com.jdasilva.Swingy.model.hero.Artifact;
import com.jdasilva.Swingy.model.hero.ArtifactManager;
import com.jdasilva.Swingy.model.hero.Hero;

public class Orc extends Enemy {
    private Random random;

    public Orc() {
        super("Orc", 45, 70, 700, "/enemyImage/orc.png");
        random = new Random();
    }

    @Override
    public void initializeArtifacts(Hero hero, ArtifactManager artifactsmanager) {
        artifacts = artifactsmanager.getArtifacts(hero.getHeroClass(), 3);
    }

    @Override
    public Artifact getArtifact() {
        return artifacts.get(random.nextInt(artifacts.size()));
    }
}
