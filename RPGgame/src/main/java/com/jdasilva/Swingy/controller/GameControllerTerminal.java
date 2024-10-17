package com.jdasilva.Swingy.controller;

import com.jdasilva.Swingy.model.enemy.Enemy;
import com.jdasilva.Swingy.model.hero.Artifact;
import com.jdasilva.Swingy.model.hero.Hero;
import com.jdasilva.Swingy.model.hero.HeroClass;
import com.jdasilva.Swingy.model.map.Gamemap;
import com.jdasilva.Swingy.view.ConsoleView;

public class GameControllerTerminal extends GameControllerBase {
   private ConsoleView view;

    public GameControllerTerminal(){
        super();
        view = new ConsoleView();
        initializeGame();
        startGame();
    }

    @Override
    protected String getCharacterChoice(){
        return view.getCharacterChoice();
    }

    @Override
    protected Hero createNewHero(){
        String name = view.getHeroName();
        HeroClass heroClass = view.getHeroClass();
        hero = new Hero(name, heroClass);
        hero.saveHero();
        return hero;
    }

    @Override
    protected Hero selectLoadHero(){
        String file = view.askForFile();
        if(file == null){
            view.displayMessage("No file selected");
            initializeGame();
        }
        return Hero.loadHero(1);
    }

    @Override
    protected boolean moveHero(Gamemap map){
        String dir;
        view.displayMap(map.getMap(), map.getVisited());
        do{
            view.showDirectionOptions();
            dir = view.getInput();
        } while(!dir.equalsIgnoreCase("N") && !dir.equalsIgnoreCase("E") && !dir.equalsIgnoreCase("S") && !dir.equalsIgnoreCase("W") && !dir.equalsIgnoreCase("exit"));
        if (dir.equalsIgnoreCase("exit"))
            close();
        return moveMap(dir);
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
        String  input;
        view.showEnemy(enemy);
        do{
            view.showBattleOptions();
            input = view.getInput();
        } while (!input.equals("1") && !input.equals("2") && !input.equalsIgnoreCase("exit"));
        if (input.equalsIgnoreCase("exit"))
            close();
        if (input.equals("2"))
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
                view.displayMessage("");
            }
        }

        while(!hero.isDead() && !enemy.isDead()){
            view.heroLife(hero);
            view.enemyLife(enemy);
            if(input.equals("1") || input.equals("2"))
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
                view.displayMessage("");
            }
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        if(!hero.isDead()){
            view.displayMessage("You have defeated " + enemy.getName());
            if(hero.setExperience(enemy.getExperience()))
            {
                view.levelup(hero);
            }
            if(artifactFound()){
                Artifact artifact = enemy.getArtifact();
                view.showArtifactFound(artifact);
                if(view.wantstoCollectArtifact())
                {
                    hero.equipArtifact(artifact);
                    view.equipArtifact(artifact);
                    view.displayMessage("Your hero stats: ");
                    view.displayMessage(hero.toString());
                }
            }
        }
        hero.saveHero();
    }

    @Override
    protected void retryGame(){
        do{
            view.displayMessage("Do you want to play again? (y/n)");
            String input = view.getInput();
            if(input.equalsIgnoreCase("y")){
                startGame();
            }else if(input.equalsIgnoreCase("n")){
                view.displayMessage("Goodbye!");
                close();
            }
        } while(true);
    }

    public void close(){
        view.closeScanner();
        System.exit(0);
    }
}
