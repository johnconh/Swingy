package com.jdasilva.Swingy.view;

import java.util.Scanner;

import com.jdasilva.Swingy.model.enemy.Enemy;
import com.jdasilva.Swingy.model.hero.Artifact;
import com.jdasilva.Swingy.model.hero.Hero;

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
        System.out.println(enemy.getName() + " life: " + enemy.getHitPoints());
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
        System.out.println("Congratulations! You have leveled up to level " + hero.getLevel());
        System.out.println("Your stats have been increased!");
        System.out.println("Attack: " + hero.getAttack());
        System.out.println("Defense: " + hero.getDefense());
        System.out.println("Hit Points: " + hero.getHitPoints());
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
}
