package com.jdasilva.Swingy;

//import com.jdasilva.Swingy.controller.GameControllerGUI;
import com.jdasilva.Swingy.controller.GameControllerTerminal;
import com.jdasilva.Swingy.model.hero.Hero;
import com.jdasilva.Swingy.model.hero.HeroClass;

public class App 
{
    public static void main( String[] args)
    {
        Hero hero = new Hero("john", HeroClass.WARRIOR);
        GameControllerTerminal game = new GameControllerTerminal(hero);
        //GameControllerGUI game = new GameControllerGUI(hero);
        game.startGame();
    }
}
