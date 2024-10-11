package com.jdasilva.Swingy.controller;

import com.jdasilva.Swingy.view.GUIView;
import com.jdasilva.Swingy.model.hero.Artifact;
import com.jdasilva.Swingy.model.hero.Hero;
import com.jdasilva.Swingy.model.hero.HeroClass;
import com.jdasilva.Swingy.model.map.Gamemap;
import com.jdasilva.Swingy.model.enemy.Enemy;

public class GameControllerGUI  extends GameControllerBase{
    private GUIView view;

    public GameControllerGUI(){
        super(); 
        view = new GUIView(this);
        initializeGame();
        view.initGUI();
        view.initializeHero(hero);
        startGame();
    }

    public int getSizeMap(){

        return map.getSize();
    }

    @Override
    protected String getCharacterChoice(){
        return view.getCharacterChoice();
    }

    @Override
    protected Hero createNewHero(){
        String name = view.getHeroName();
        if(name == null){
            view.displayMessage("No name entered");
            initializeGame();
            return null;
        }
        HeroClass heroClass = view.getHeroClass();
        if (heroClass == null){
            view.displayMessage("No class selected");
            initializeGame();
            return null;   
        }
        hero = new Hero(name, heroClass);
        hero.saveHero();
        return hero;
    }

    @Override
    protected Hero selectLoadHero(){
        String file = view.askForFile();
        
        if (file == null){
            view.displayMessage("No file selected");
            initializeGame();
            return null;
        }

        return Hero.loadHero(file);
    }

    @Override
    protected boolean moveHero(Gamemap map){
        view.displayMap(map.getMap(), map.getVisited());
        String direction = view.getDirection();
        return moveMap(direction);
    }

    @Override
    protected void showGameOver(){
        view.gameOver();
    }

    @Override
    protected void showWin(){
        view.win();
    }

    @Override
    protected void BattleSequence(Enemy enemy)
    {
        view.initializeEnemy(enemy);
        String action = view.getBattleAction();

        if(action.equalsIgnoreCase("Run"))
        {
            if (run())
            {
                view.displayMessage("You ran away!");
                return;
            }else{
                view.displayMessage("You failed to run away!");
                int herodamage = Math.max(0, enemy.getAttack() - hero.getDefense());
                hero.takeDamage(herodamage);
                view.updateHeroLifeBar(hero.getLife(), hero.getHitPoints());
            }
        }

        while(!hero.isDead() && !enemy.isDead())
        {
            view.updateHeroStats(hero);
            view.updateEnemyStats(enemy);

            if(action.equalsIgnoreCase("Run") || action.equalsIgnoreCase("Fight"))
            {
                int enemydamage = hero.getAttack();
                enemy.takeDamage(enemydamage);
                view.updateEnemyLifeBar(enemy.getLife(), enemy.getHitPoints());
                if(enemy.isDead()){
                    break;
                }else{
                    int herodamage = Math.max(0, enemy.getAttack() - hero.getDefense());
                    hero.takeDamage(herodamage);
                    view.updateHeroLifeBar(hero.getLife(), hero.getHitPoints());
                }
            }
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        if(!hero.isDead())
        {
            view.displayMessage("You have defeated " + enemy.getName());
            if(hero.setExperience(enemy.getExperience()))
            {
                view.levelup(hero);
                view.updateHeroStats(hero);
            }
            if(artifactFound())
            {
                Artifact artifact = enemy.getArtifact();
                view.showArtifactFound(artifact);
                if(view.wantstoCollectArtifact())
                {
                    hero.equipArtifact(artifact);
                    view.equipArtifact(artifact);
                    view.updateHeroStats(hero);
                }
            }
        }else
        {
            view.displayMessage("You have been defeated by " + enemy.getName());
        }
        view.hideEnemyPanel();
        hero.saveHero();
    }
}
