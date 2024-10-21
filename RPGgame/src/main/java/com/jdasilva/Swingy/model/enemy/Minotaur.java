package com.jdasilva.Swingy.model.enemy;

import java.util.Random;
import com.jdasilva.Swingy.model.hero.Artifact;
import com.jdasilva.Swingy.model.hero.ArtifactManager;
import com.jdasilva.Swingy.model.hero.Hero;

public class Minotaur extends Enemy {
    private Random random;

    public Minotaur() {
        super("Minotaur", 50, 70, 1100, "/enemyImage/minotaur.png");
        random = new Random();
    }

    @Override
    public void initializeArtifacts(Hero hero, ArtifactManager artifactsmanager) {
        artifacts = artifactsmanager.getArtifacts(hero.getHeroClass(), 4);
    }

    @Override
    public Artifact getArtifact() {
        return artifacts.get(random.nextInt(artifacts.size()));
    }
}
