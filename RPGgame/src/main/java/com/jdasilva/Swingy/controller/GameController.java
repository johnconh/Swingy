package com.jdasilva.Swingy.controller;

import com.jdasilva.Swingy.model.enemy.Enemy;
import com.jdasilva.Swingy.model.enemy.EnemyFactoy;
import com.jdasilva.Swingy.model.hero.Artifact;
import com.jdasilva.Swingy.model.hero.ArtifactManager;
import com.jdasilva.Swingy.model.hero.Hero;
import com.jdasilva.Swingy.model.hero.HeroClass;
import com.jdasilva.Swingy.model.map.Gamemap;
import com.jdasilva.Swingy.view.ConsoleView;

public class GameController {
    private ConsoleView view;
    private Hero hero;
    private Gamemap map;
    private boolean Win;
    
    public GameController() {
        view = new ConsoleView();
        hero = new Hero("John", HeroClass.WARRIOR);
        map = new Gamemap(hero.getLevel());
        Win = false;
    }
    
    public void startGame() {
        view.displayMessage("Welcome to RPG GAME!");
        view.displayMessage("Your hero is " + hero.getName() + " the " + hero.getHeroClass().toString());
        while (GameStatus()) {
            if (!moveHero(map)) {
                Win = true;
            }
        }
    }

    private boolean GameStatus()
    {
        if (hero.isDead())
        {
            hero.resetLife();
            map.resetHeroPosition();
            view.gameOver();
            return false;
        }
        if (Win)
        {
            hero.resetLife();
            map.resetHeroPosition();
            Win = false;
            view.win();
            return false;
        }
        return true;
    }

    private boolean run() {
        return Math.random() < 0.5;
    }

    private boolean artifactFound() {
        return Math.random() < 0.5;
    }

    private boolean enemyFound() {
        return Math.random() < 0.5;
    }

    
    public void Battle(Enemy enemy) {
        String input;
        view.showEnemy(enemy);
        do{
            view.showBattleOptions();
            input = view.getInput();  
        } while (!input.equals("1") && !input.equals("2"));

        if(input.equals("2")){
            if(run()){
                view.displayMessage("You ran away!");
                return;
            } else {
                view.displayMessage("You failed to run away!");
                int herodamage = Math.max(0, enemy.getAttack() - hero.getDefense());
                hero.takeDamage(herodamage);
                view.displayMessage(enemy.getName() + " has inflicted " + herodamage + " damage to you");
                view.displayMessage("");
            }
        }

        while (!hero.isDead() && !enemy.isDead()) {
            view.heroLife(hero);
            view.enemyLife(enemy);
            if (input.equals("1") || input.equals("2")) {
                int enemydamage = hero.getAttack();
                enemy.takeDamage(enemydamage);
                view.displayMessage("You have inflicted " + enemydamage + " damage to " + enemy.getName());
                if(enemy.isDead()){
                    break;
                }else
                { 
                    int herodamage = Math.max(0, enemy.getAttack() - hero.getDefense());
                    hero.takeDamage(herodamage);
                    view.displayMessage(enemy.getName() + " has inflicted " + herodamage + " damage to you");
                }
                view.displayMessage("");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        if(!hero.isDead()){
            view.displayMessage("You have defeated " + enemy.getName());
            if(hero.setExperience(enemy.getExperience()))
                view.levelup(hero);
            if(artifactFound()){
                Artifact artifact = enemy.getArtifact();
                view.showArtifactFound(artifact);
                if(view.wantstoCollectArtifact()){
                    hero.equipArtifact(artifact);
                    view.equipArtifact(artifact);
                    view.displayMessage("Your hero stats: ");
                    view.displayMessage(hero.toString());
                }
            }
        }else{
            view.displayMessage("You have been defeated by " + enemy.getName());
        }
    }

    public void close() {
        view.closeScanner();
    }

    private boolean moveHero(Gamemap map) {
        String dir;
        view.displayMap(map.getMap(), map.getVisited());
        do{
            view.showDirectionOptions();
            dir = view.getInput();
        } while (!dir.equalsIgnoreCase("N") && !dir.equalsIgnoreCase("E") && !dir.equalsIgnoreCase("S") && !dir.equalsIgnoreCase("W"));

        if(map.moveHero(dir)){
            if(enemyFound()){
                Enemy enemy = EnemyFactoy.CreateEnemy(hero.getLevel());
                enemy.initializeArtifacts(hero, new ArtifactManager());
                Battle(enemy);
            }
            return true;
        }
        return false;
    }

}
