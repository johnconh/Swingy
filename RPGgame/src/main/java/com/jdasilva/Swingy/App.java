package com.jdasilva.Swingy;

import com.jdasilva.Swingy.controller.GameControllerGUI;
import com.jdasilva.Swingy.database.databaseManager;
//import com.jdasilva.Swingy.controller.GameControllerTerminal;

public class App 
{
    public static void main( String[] args)
    {
        new databaseManager();
        //new GameControllerTerminal();
        new GameControllerGUI();
        return;
    }
}