package com.jdasilva.Swingy.view;

import java.io.File;
import java.util.Scanner;

import com.jdasilva.Swingy.model.enemy.Enemy;
import com.jdasilva.Swingy.model.hero.Artifact;
import com.jdasilva.Swingy.model.hero.Hero;
import com.jdasilva.Swingy.model.hero.HeroClass;

public class ConsoleView implements GameView {

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
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("Create") || choice.equalsIgnoreCase("Load"))
                return choice;
            else
                System.out.println("Invalid choice. Please type 'Create' or 'Load'");
        }
    }

    public String getHeroName(){
        while(true){
            System.out.println("Enter your hero name: ");
            String name = scanner.nextLine().trim();
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

            if(scanner.hasNextInt()){
                int choice = scanner.nextInt();
                scanner.nextLine();
                if(choice < 1 || choice > 3){
                    System.out.println("Invalid input. Please enter a number between 1 and 3");
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

    public String askForFile(){
        String filename;

        while(true){
            System.out.println("Enter the name of the hero file to load (e.g., John_WARRIOR.json): ");
            filename = scanner.nextLine().trim();

            if(filename.equalsIgnoreCase("exit")){
                return null;
            }

            File file = new File(filename);
            
            if(!filename.isEmpty() &&file.exists() && !file.isDirectory()){
                return filename;
            }else{
                System.out.println("Invalid file name. Please enter a valid file name.");
            }
        }
    }
}
