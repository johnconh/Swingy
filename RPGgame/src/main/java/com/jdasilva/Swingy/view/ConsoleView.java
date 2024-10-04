package com.jdasilva.Swingy.view;

import java.util.Scanner;

import com.jdasilva.Swingy.model.enemy.Enemy;
import com.jdasilva.Swingy.model.hero.Artifact;
import com.jdasilva.Swingy.model.hero.Hero;

public class ConsoleView {

    private Scanner scanner;

    public ConsoleView() {
        scanner = new Scanner(System.in);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void showEnemy(Enemy enemy){
        System.out.println(enemy.getName() + " has appeared!");
    }

    public void showBattleOptions(){
        System.out.println("1. Fight");
        System.out.println("2. Run");
    }

    public void showDirectionOptions(){
        System.out.println("1.N (North)");
        System.out.println("2.E (East)");
        System.out.println("3.S (South)");
        System.out.println("4.W (West)");
    }

    public void HeroLife(Hero hero){
        System.out.println("Your life: " + hero.getHitPoints());
    }

    public void EnemyLife(Enemy enemy){
        System.out.println(enemy.getName() + " life: " + enemy.getHitPoints());
    }

    public void showArtifactFound(Artifact artifact){
        System.out.println("You found a " + artifact.getName() + "!");
    }

    public boolean wantstoCollectArtifact(){
        System.out.println("Do you want to collect the artifact? (y/n)");
        String input = scanner.nextLine();
        return input.equalsIgnoreCase("y");
    }

    public void equipArtifact(Artifact artifact){
        System.out.println("You have equipped " + artifact.getName());
    }

    public String getInput(){
        return scanner.nextLine();
    }

    public void GameOver(){
        System.out.println("GAME OVER!");
    }

    public void Levelup(Hero hero){
        System.out.println("Congratulations! You have leveled up to level " + hero.getLevel());
        System.out.println("Your stats have been increased!");
        System.out.println("Attack: " + hero.getAttack());
        System.out.println("Defense: " + hero.getDefense());
        System.out.println("Hit Points: " + hero.getHitPoints());
    }

    public void closeScanner(){
        scanner.close();
    }

    public void displayMap(char[][] cs){
        for (int i = 0; i < cs.length; i++) {
            for (int j = 0; j < cs[i].length; j++) {
                System.out.print(cs[i][j]);
            }
            System.out.println();
        }
    }
}
