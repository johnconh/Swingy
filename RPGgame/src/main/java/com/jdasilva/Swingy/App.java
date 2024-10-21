package com.jdasilva.Swingy;

import com.jdasilva.Swingy.controller.GameControllerGUI;
import com.jdasilva.Swingy.database.databaseManager;
import com.jdasilva.Swingy.controller.GameControllerTerminal;

public class App 
{
    public static void main( String[] args)
    {
        if (args.length == 1) {
            new databaseManager();
            if (args[0].equalsIgnoreCase("console")) {
                System.out.println("Launching in console mode...");
                new GameControllerTerminal();
            } else if (args[0].equalsIgnoreCase("gui")) {
                System.out.println("Launching in GUI mode...");
                new GameControllerGUI();
            } else {
                System.out.println("Invalid argument. Use 'console' or 'gui'.");
            }
        } else {
            System.out.println("Please provide an argument: 'console' or 'gui'.");
        }
        return;
    }
}