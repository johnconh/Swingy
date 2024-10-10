package com.jdasilva.Swingy;

//import com.jdasilva.Swingy.controller.GameControllerGUI;
import com.jdasilva.Swingy.controller.GameControllerTerminal;

public class App 
{
    public static void main( String[] args)
    {
        GameControllerTerminal game = new GameControllerTerminal();
        //GameControllerGUI game = new GameControllerGUI();
        game.startGame();
    }
}