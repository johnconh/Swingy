package com.jdasilva.Swingy.model.enemy;

import java.util.Random;
import com.jdasilva.Swingy.model.hero.Artifact;
import com.jdasilva.Swingy.model.hero.ArtifactManager;
import com.jdasilva.Swingy.model.hero.Hero;

public class Dragon extends Enemy {
    private Random random;

    public Dragon() {
        super("Dragon", 70, 110, 1500, "/enemyImage/dragon.png");
        random = new Random();
    }

    @Override
    public void initializeArtifacts(Hero hero, ArtifactManager artifactsmanager) {
        artifacts = artifactsmanager.getArtifacts(hero.getHeroClass(), 5);
    }

    @Override
    public Artifact getArtifact() {
        return artifacts.get(random.nextInt(artifacts.size()));
    } 
}
