package com.jdasilva.Swingy.model.enemy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyFactoy {

    private static Random random = new Random();

    public static List<Enemy> CreateEnemy(int herolevel) {
        List<Enemy> enemies = new ArrayList<>();

        switch (herolevel) {
            case 1:
                enemies.add(new Rat());
                enemies.add(new Goblin());
                break;
            case 2:
                enemies.add(new Goblin());
                enemies.add(new Orc());
                break;
            case 3:
                enemies.add(new Orc());
                enemies.add(new Minotaur());
                break;
            case 4:
                enemies.add(new Minotaur());
                enemies.add(new Dragon());
                break;
            case 5:
                enemies.add(new Dragon());
                enemies.add(new Demonlord());
                break;
            case 6:
                enemies.add(new Rat());
                enemies.add(new Goblin());
                enemies.add(new Orc());
                enemies.add(new Minotaur());
                enemies.add(new Dragon());
                enemies.add(new Demonlord());
                break;
            default:
                break;
        }
        return enemies;
    }

    public static Enemy getEnemy(List<Enemy> enemies) {
        return enemies.get(random.nextInt(enemies.size()));
    }
}
