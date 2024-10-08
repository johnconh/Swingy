package com.jdasilva.Swingy.controller;

import com.jdasilva.Swingy.model.enemy.Enemy;
import com.jdasilva.Swingy.model.enemy.EnemyFactoy;
import com.jdasilva.Swingy.model.hero.ArtifactManager;
import com.jdasilva.Swingy.model.hero.Hero;
import com.jdasilva.Swingy.model.map.Gamemap;

public abstract class GameControllerBase {
    protected Hero hero;
    protected boolean win;
    protected Gamemap map;


    public GameControllerBase(Hero hero) {
        this.hero = hero;
        this.map = new Gamemap(hero.getLevel());
        this.win = false;
    }

    public void startGame() {
        while (gameStatus()) {
            if (!moveHero(map)) {
                win = true;
            }
        }
    }

    protected abstract boolean moveHero(Gamemap map);

    protected void resetGame() {
        hero.resetLife();
        map.resetHeroPosition();
    }

    protected boolean gameStatus() {
        if (hero.isDead()) {
            resetGame();
            showGameOver();
            return false;
        }
        if (win) {
            resetGame();
            win = false;
            showWin();
            return false;
        }
        return true;
    }

    protected abstract void showGameOver();
    protected abstract void showWin();
    

    protected boolean run() {
        return Math.random() < 0.5;
    }
    
    protected boolean artifactFound() {
        return Math.random() < 0.5;
    }

    protected boolean enemyFound() {
        return Math.random() < 0.5;
    }

    protected void battle(Enemy enemy) {
        BattleSequence(enemy);
    }

    protected abstract void BattleSequence(Enemy enemy);

    public boolean moveMap(String direction)
    {
        if(map.moveHero(direction)){
            if(enemyFound()){
                Enemy enemy = EnemyFactoy.CreateEnemy(hero.getLevel());
                enemy.initializeArtifacts(hero, new ArtifactManager());
                battle(enemy);
            }
            return true;
        }
        return false;
    }
}
