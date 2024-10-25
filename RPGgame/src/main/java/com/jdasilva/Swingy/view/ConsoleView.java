package com.jdasilva.Swingy.view;

import java.util.Scanner;
import java.util.List;
import com.jdasilva.Swingy.model.enemy.Enemy;
import com.jdasilva.Swingy.model.hero.Artifact;
import com.jdasilva.Swingy.model.hero.Hero;
import com.jdasilva.Swingy.model.hero.HeroClass;

public class ConsoleView {

    private Scanner scanner;
  
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";

    public ConsoleView() {
        scanner = new Scanner(System.in);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void showEnemy(Enemy enemy){
        System.out.println();
        System.out.println(enemy.getName() + " has appeared!");
    }

    public void showBattleOptions(){
        System.out.println("Choose your action (1,2):");
        System.out.println("1. Fight");
        System.out.println("2. Run");
    }

    public void showDirectionOptions(){
        System.out.println("Choose your direction (N,E,S,W):");
        System.out.println("N. North");
        System.out.println("E. East");
        System.out.println("S. South");
        System.out.println("W. West");
        
    }

    public void heroLife(Hero hero){
        System.out.println("Your life: " + hero.getLife());
    }

    public void enemyLife(Enemy enemy){
        System.out.println(enemy.getName() + " life: " + enemy.getLife());
    }

    public void showArtifactFound(Artifact artifact){
        System.out.println();
        System.out.println("You found a " + artifact.getName() + "!");
    }

    public boolean wantstoCollectArtifact(){
        System.out.println("Do you want to collect the artifact? (y/n)");
        String input = scanner.nextLine();
        return input.equalsIgnoreCase("y");
    }

    public void equipArtifact(Artifact artifact){
        System.out.println("You have equipped " + artifact.getName());
        System.err.println();
    }

    public String getInput(){
        if(!scanner.hasNextLine())
            System.exit(0);
        return scanner.nextLine();
    }

    public void gameOver(){
        System.out.println("GAME OVER!");
    }

    public void win(){
        System.out.println("You have won!");
    }
    
    public void levelup(Hero hero){
        System.out.println();
        System.out.println("🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊");
        System.out.println("Congratulations! You have leveled up to level " + hero.getLevel());
        System.out.println("Your stats have been increased!");
        System.out.println("Attack: " + hero.getAttack());
        System.out.println("Defense: " + hero.getDefense());
        System.out.println("Hit Points: " + hero.getHitPoints());
        System.out.println("🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊🟊");
        System.out.println();
    }

    public void closeScanner(){
        scanner.close();
    }

    public void displayMap(char[][] map, boolean[][] visited){
        System.out.println();
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map.length; j++){
                if(map[i][j] == 'P'){
                    System.out.print(GREEN + map[i][j] + RESET);
                }else if (visited[i][j]){
                    System.out.print(RED + map[i][j] + RESET);
                }else{
                    System.out.print(map[i][j]);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public String getCharacterChoice(){
        while(true){
            System.out.println("Do you want to create a new hero or load a saved hero?");
            System.out.println("Create");
            System.out.println("Load");
            String choice = getInput();
            if(choice.equalsIgnoreCase("exit"))
            {
                closeScanner();
                System.exit(0);
            }
            if (choice.equalsIgnoreCase("Create") || choice.equalsIgnoreCase("Load"))
                return choice;
            else
                System.out.println("Invalid choice. Please type 'Create' or 'Load'");
        }
    }

    public String getHeroName(){
        while(true){
            System.out.println("Enter your hero name: ");
            String name = getInput().trim();
            if(!name.isEmpty()){
                return name;    
            }
        }
    }

    public HeroClass getHeroClass(){
        while(true){
            System.out.println("Choose your hero class: ");
            System.out.println("1. Warrior");
            System.out.println("2. Paladin");
            System.out.println("3. Rogue");
            String input = getInput().trim();
            if (input.isEmpty())
            {
                System.out.println("Invalid input. Please enter a number between 1 and 3");
                continue;
            }
            if(input.length() == 1 && Character.isDigit(input.charAt(0))){
                int choice = Integer.parseInt(input);
                if(choice < 1 || choice > 3){
                    System.out.println("Invalid input. Please enter a number between 1 and 3");
                    scanner.nextLine();
                    continue;
                }
                switch(choice){
                    case 1:
                        return HeroClass.WARRIOR;
                    case 2:
                        return HeroClass.PALADIN;
                    case 3:
                        return HeroClass.ROGUE;
                    default:
                        return HeroClass.WARRIOR;
                }
            }else{
                scanner.nextLine();
                System.out.println("Invalid input. Please enter a number between 1 and 3");
            }
        }
    }

    public int loadHeroFromDB(){
        List<Hero> heroes = Hero.getAllHeroes();
        if (heroes.isEmpty()){
            System.out.println("<<< No heroes found >>>");
            return -1;
        }
        showHeroList(heroes);
        int choice = -1;
        do{
            System.out.println("Choose a hero to load: ");
            while(!scanner.hasNextInt()){
                System.out.println("Invalid input. Please enter a number");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine();
            if(choice < 1 || choice > heroes.size()){
                System.out.println("Invalid input. Please enter a number between 1 and " + heroes.size());
                choice = -1;
            }
        }while (choice == -1);
        return heroes.get(choice - 1).getID();
    }

    private void showHeroList(List<Hero> heroes) {
        System.out.println("Available Heroes:");
        for (int i = 0; i < heroes.size(); i++) {
            Hero hero = heroes.get(i);
            System.out.println((i + 1) + ". " + hero.getName() + " (Class: " + hero.getHeroClass() + ", Level: " + hero.getLevel() + ")");
        }
    }
}
