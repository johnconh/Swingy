package com.jdasilva.Swingy;

import com.jdasilva.Swingy.controller.GameControllerGUI;
import com.jdasilva.Swingy.model.hero.Hero;
import com.jdasilva.Swingy.model.hero.HeroClass;

public class App 
{
    public static void main( String[] args)
    {
        Hero hero = new Hero("john", HeroClass.WARRIOR);
        GameControllerGUI game = new GameControllerGUI(hero);
        game.startGame();
    }
}
