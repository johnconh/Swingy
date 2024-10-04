package com.jdasilva.Swingy;

import java.util.Scanner;

import com.jdasilva.Swingy.controller.GameController;

public class App 
{
    public static void main( String[] args )
    {
        GameController gameController = new GameController();

        Scanner scanner = new Scanner(System.in);
        String input;

        do{
            gameController.startGame();
            System.out.println("Do you want to play again? (y/n)");
            input = scanner.nextLine();
        }while(!input.equalsIgnoreCase("n"));
        System.out.println("Goodbye!");
        gameController.close();
        if(scanner != null){
            scanner.close();
        }
    }
}
