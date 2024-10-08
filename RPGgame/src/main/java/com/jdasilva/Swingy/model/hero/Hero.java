package com.jdasilva.Swingy.model.hero;

public class Hero {
    private String name;
    private HeroClass heroClass;
    private int level;
    private int experience;
    private int attack;
    private int defense;
    private int hitPoints;
    private int life;
    private Artifact Weapon;
    private Artifact Armor;
    private Artifact Helm;

    public Hero(String name, HeroClass heroClass) {
        this.name = name;
        this.heroClass = heroClass;
        this.level = 1;
        this.experience = heroClass.getExperience();
        this.attack = heroClass.getAttack();
        this.defense = heroClass.getDefense();
        this.hitPoints = heroClass.getHitPoints();
        this.life = hitPoints;
        this.Weapon = null;
        this.Armor = null;
        this.Helm = null;
    }

    public Artifact getWeapon() {
        return Weapon;
    }

    public Artifact getArmor() {
        return Armor;
    }

    public Artifact getHelm() {
        return Helm;
    }
    
    public String getName() {
        return name;
    }

    public HeroClass getHeroClass() {
        return heroClass;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
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

    public int getLife() {
        return life;
    }

    public int takeDamage(int damage) {
        life -= damage;
        return hitPoints;
    }

    public boolean isDead() {
        return life <= 0;
    }

    public void resetLife()
    {
        this.life = hitPoints;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean setExperience(int experience) {
        
        if (this.level >= 5) {
            return false;
        }

        this.experience += experience;

        if (this.experience >= (this.level * 1000 + Math.pow(this.level - 1, 2) * 450)) {
            this.level++;
            this.attack += 5;
            this.defense += 5;
            this.hitPoints += 25;
            return true;
        }
        return false;
    }

    public void equipArtifact(Artifact artifact){
     
        switch (artifact.getType()) {
            case WEAPON:
                if (Weapon != null) {
                    attack -= Weapon.getAttack();
                }
                Weapon = artifact;
                attack += Weapon.getAttack();
                break;
            case ARMOR:
                if (Armor != null) {
                    defense -= Armor.getDefense();
                }
                Armor = artifact;
                defense += Armor.getDefense();
                break;
            case HELM:
                if (Helm != null) {
                    hitPoints -= Helm.getHitPoints();
                }
                Helm = artifact;
                hitPoints += Helm.getHitPoints();
                break;
        }
    }

    @Override
    public String toString() {
        return "Hero{" +
                "name='" + name + '\'' +
                ", heroClass=" + heroClass +
                ", level=" + level +
                ", experience=" + experience +
                ", attack=" + attack +
                ", defense=" + defense +
                ", hitPoints=" + hitPoints +
                ", Weapon=" + (Weapon != null ? Weapon.getName() : "None") +
                ", Armor=" + (Armor != null ? Armor.getName() : "None") +
                ", Helm=" + (Helm != null ? Helm.getName() : "None") +
                '}';
    }
}
