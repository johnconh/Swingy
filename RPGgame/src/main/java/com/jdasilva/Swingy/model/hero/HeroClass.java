package com.jdasilva.Swingy.model.hero;

public enum HeroClass {
    
    WARRIOR(3, 15, 380, 0),
    ROGUE(15, 3, 350, 0),
    PALADIN(5, 5, 400, 0);

    private final int attack;
    private final int defense;
    private final int hitPoints;
    private final int experience;

    HeroClass(int attack, int defense, int hitPoints, int experience) {
        this.attack = attack;
        this.defense = defense;
        this.hitPoints = hitPoints;
        this.experience = experience;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getExperience() {
        return experience;
    }
}
