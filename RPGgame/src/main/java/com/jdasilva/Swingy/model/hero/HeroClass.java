package com.jdasilva.Swingy.model.hero;

public enum HeroClass {
    
    WARRIOR(3, 15, 380, 0, "/heroImage/warrior.png"),
    ROGUE(15, 3, 350, 0, "rogue.png"),
    PALADIN(5, 5, 400, 0, "paladin.png"),;

    private final int attack;
    private final int defense;
    private final int hitPoints;
    private final int experience;
    private final String image;

    HeroClass(int attack, int defense, int hitPoints, int experience, String image) {
        this.attack = attack;
        this.defense = defense;
        this.hitPoints = hitPoints;
        this.experience = experience;
        this.image = image;
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

    public String getImage() {
        return image;
    }
}
