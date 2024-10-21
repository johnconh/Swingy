package com.jdasilva.Swingy;

import java.util.Scanner;
import com.jdasilva.Swingy.controller.GameControllerGUI;
import com.jdasilva.Swingy.database.databaseManager;
import com.jdasilva.Swingy.controller.GameControllerTerminal;

public class App 
{
    public static void main( String[] args)
    {
        new databaseManager();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose the mode: 'console' or 'gui'");
        String input = "";
        while (true) {
            input = scanner.nextLine().toLowerCase();
            if(input.equals("console")){
                new GameControllerTerminal();
                break;
            }
            else if(input.equals("gui")){
                new GameControllerGUI();
                break;
            }
            else
                System.out.println("Please choose the mode: 'console' or 'gui'"); 
        }
        scanner.close();
        return;
    }
}