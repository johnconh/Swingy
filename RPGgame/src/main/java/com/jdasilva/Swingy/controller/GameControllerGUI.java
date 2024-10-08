package com.jdasilva.Swingy.controller;

import com.jdasilva.Swingy.view.GUIView;
import com.jdasilva.Swingy.model.hero.Artifact;
import com.jdasilva.Swingy.model.hero.Hero;
import com.jdasilva.Swingy.model.map.Gamemap;
import com.jdasilva.Swingy.model.enemy.Enemy;

public class GameControllerGUI  extends GameControllerBase{
    private GUIView view;

    public GameControllerGUI(Hero hero){
        super(hero);
        view = new GUIView(this);
    }

    public int getSizeMap(){

        return map.getSize();
    }

    @Override
    protected boolean moveHero(Gamemap map){
        view.updateMap(map.getMap(), map.getVisited());
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
        view.showEnemy(enemy);
        String action = view.getBattleAction();

        if(action.equals("Run"))
        {
            if (run())
            {
                view.displayMessage("You ran away!");
                return;
            }else{
                view.displayMessage("You failed to run away!");
                int herodamage = Math.max(0, enemy.getAttack() - hero.getDefense());
                hero.takeDamage(herodamage);
                view.displayMessage(enemy.getName() + " has inflicted " + herodamage + " damage to you");
            }

            while(!hero.isDead() && !enemy.isDead())
            {
                view.heroLife(hero);
                view.enemyLife(enemy);

                if(action.equals("Run") || action.equals("Fight"))
                {
                    int enemydamage = hero.getAttack();
                    enemy.takeDamage(enemydamage);
                    view.displayMessage("You have inflicted " + enemydamage + " damage to " + enemy.getName());
                    if(enemy.isDead()){
                        break;
                    }else{
                        int herodamage = Math.max(0, enemy.getAttack() - hero.getDefense());
                        hero.takeDamage(herodamage);
                        view.displayMessage(enemy.getName() + " has inflicted " + herodamage + " damage to you");
                    }
                }
            }

            if(!hero.isDead())
            {
                view.displayMessage("You have defeated " + enemy.getName());
                if(hero.setExperience(enemy.getExperience()))
                    view.levelup(hero);
                if(artifactFound())
                {
                    Artifact artifact = enemy.getArtifact();
                    view.showArtifactFound(artifact);
                    if(view.wantstoCollectArtifact())
                    {
                        hero.equipArtifact(artifact);
                        view.equipArtifact(artifact);
                        view.heroStats(hero);
                    }
                }
            }else
            {
                view.displayMessage("You have been defeated by " + enemy.getName());
            }
        }
    }
}
