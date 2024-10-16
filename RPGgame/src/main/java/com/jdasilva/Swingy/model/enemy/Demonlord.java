package com.jdasilva.Swingy.model.enemy;

import java.util.Random;
import com.jdasilva.Swingy.model.hero.Artifact;
import com.jdasilva.Swingy.model.hero.ArtifactManager;
import com.jdasilva.Swingy.model.hero.Hero;

public class Demonlord extends Enemy {

    private Random random;

    public Demonlord() {
        super("Demonlord", 160, 1000, 42, "/enemyImage/demonlord.png");
        random = new Random();
    }

    @Override
    public void initializeArtifacts(Hero hero, ArtifactManager artifactsmanager) {
        artifacts = artifactsmanager.getArtifacts(hero.getHeroClass(), 6);
    }

    @Override
    public Artifact getArtifact() {
        return artifacts.get(random.nextInt(artifacts.size()));
    }
}