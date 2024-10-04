package com.jdasilva.Swingy.model.enemy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyFactoy {

    private static Random random = new Random();

    public static Enemy CreateEnemy(int herolevel) {
        List<Enemy> enemies = new ArrayList<>();

        if(herolevel >= 0){
            enemies.add(new Goblin());
        }
        if (herolevel <= 5){
            enemies.add(new Goblin());
        } 
        if(herolevel > 5){
            enemies.add(new Goblin());
        }
        return enemies.get(random.nextInt(enemies.size()));
    }
}
