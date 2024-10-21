package com.jdasilva.Swingy.model.hero;

import com.jdasilva.Swingy.database.databaseManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Hero {
    private int id;
    private String name;
    private HeroClass heroClass;
    private int level;
    private int experience;
    private int attack;
    private int defense;
    private int hitPoints;
    private int life;
    private Artifact weapon;
    private Artifact armor;
    private Artifact helm;
    private String image;
    private String URL;
    private String DB_NAME;
    private String USER;
    private String PASSWORD;

    public Hero(String name, HeroClass heroClass) {
        this.name = name;
        this.heroClass = heroClass;
        this.level = 1;
        this.experience = heroClass.getExperience();
        this.attack = heroClass.getAttack();
        this.defense = heroClass.getDefense();
        this.hitPoints = heroClass.getHitPoints();
        this.life = hitPoints;
        this.weapon = null;
        this.armor = null;
        this.helm = null;
        this.image = heroClass.getImage();

        this.URL = databaseManager.getURL();
        this.DB_NAME = databaseManager.getDB_NAME();
        this.USER = databaseManager.getUSER();
        this.PASSWORD = databaseManager.getPASSWORD();
    }

    public void equipArtifact(Artifact artifact){
        
        switch (artifact.getType()) {
            case WEAPON:
            if (weapon != null) {
                    attack -= weapon.getAttack();
                }
                weapon = artifact;
                attack += weapon.getAttack();
                break;
                case ARMOR:
                if (armor != null) {
                    defense -= armor.getDefense();
                }
                armor = artifact;
                defense += armor.getDefense();
                break;
                case HELM:
                if (helm != null) {
                    hitPoints -= helm.getHitPoints();
                }
                helm = artifact;
                hitPoints += helm.getHitPoints();
                break;
            }
        }
        
    public void saveHero(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            connection = DriverManager.getConnection(this.URL + this.DB_NAME, this.USER, this.PASSWORD);
            
            String heroQuery = "INSERT INTO heroes (name, class, level, experience, attack, defense, hitPoints, image) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING id;";
            statement = connection.prepareStatement(heroQuery);
            statement.setString(1, this.getName());
            statement.setString(2, this.getHeroClass().toString());
            statement.setInt(3, this.getLevel());
            statement.setInt(4, this.getExperience());
            statement.setInt(5, this.getAttack());
            statement.setInt(6, this.getDefense());
            statement.setInt(7, this.getHitPoints());
            statement.setString(8, this.getImage());
            
            resultSet = statement.executeQuery();
            if(resultSet != null && resultSet.next()){
                this.setID(resultSet.getInt(1));
            }
            
            String artiftsQuery = "INSERT INTO artifacts (hero_id, name, type, attack, defense, hitPoints)" +
            "VALUES (?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(artiftsQuery);
            
            if(weapon != null){
                statement.setInt(1, this.getID());
                statement.setString(2, weapon.getName());
                statement.setString(3, weapon.getType().toString());
                statement.setInt(4, weapon.getAttack());
                statement.setInt(5, weapon.getDefense());
                statement.setInt(6, weapon.getHitPoints());
                statement.executeUpdate();
            }
            
            if(armor != null){
                statement.setInt(1, this.getID());
                statement.setString(2, armor.getName());
                statement.setString(3, armor.getType().toString());
                statement.setInt(4, armor.getAttack());
                statement.setInt(5, armor.getDefense());
                statement.setInt(6, armor.getHitPoints());
                statement.executeUpdate();
            }
            
            if(helm != null){
                statement.setInt(1, this.getID());
                statement.setString(2, helm.getName());
                statement.setString(3, helm.getType().toString());
                statement.setInt(4, helm.getAttack());
                statement.setInt(5, helm.getDefense());
                statement.setInt(6, helm.getHitPoints());
                statement.executeUpdate();
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(connection != null) connection.close();
                if(statement != null) statement.close();
                if(resultSet != null) resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateHero(){
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = DriverManager.getConnection(this.URL + this.DB_NAME, this.USER, this.PASSWORD);
            
            String heroQuery = "UPDATE heroes SET name = ?, class = ?, level = ?, experience = ?, attack = ?, defense = ?, hitPoints = ?, image = ? where id = ?";
            statement = connection.prepareStatement(heroQuery);
            statement.setString(1, this.getName());
            statement.setString(2, this.getHeroClass().toString());
            statement.setInt(3, this.getLevel());
            statement.setInt(4, this.getExperience());
            statement.setInt(5, this.getAttack());
            statement.setInt(6, this.getDefense());
            statement.setInt(7, this.getHitPoints());
            statement.setString(8, this.getImage());
            statement.setInt(9, this.getID());
            statement.executeUpdate();
            statement.close();

            updateOrInsertArtifact(connection, "WEAPON", weapon);
            updateOrInsertArtifact(connection, "ARMOR", armor);
            updateOrInsertArtifact(connection, "HELM", helm);
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(connection != null) connection.close();
                if(statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateOrInsertArtifact(Connection connection, String artifactType, Artifact artifact) throws SQLException{
        if (artifact == null) return;

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try{
            String artifactQuery = "SELECT COUNT (*) FROM artifacts WHERE hero_id = ? AND type = ?";
            statement = connection.prepareStatement(artifactQuery);
            statement.setInt(1, this.getID());
            statement.setString(2, artifactType);
            resultSet = statement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);

            if (count > 0) {
                String updateQuery = "UPDATE artifacts SET name = ?, attack = ?, defense = ?, hitPoints = ? WHERE hero_id = ? AND type = ?";
                statement = connection.prepareStatement(updateQuery);
                statement.setString(1, artifact.getName());
                statement.setInt(2, artifact.getAttack());
                statement.setInt(3, artifact.getDefense());
                statement.setInt(4, artifact.getHitPoints());
                statement.setInt(5, this.getID());
                statement.setString(6, artifactType);
                statement.executeUpdate();
            } else {
                String insertQuery = "INSERT INTO artifacts (hero_id, name, type, attack, defense, hitPoints) VALUES (?, ?, ?, ?, ?, ?)";
                statement = connection.prepareStatement(insertQuery);
                statement.setInt(1, this.getID());
                statement.setString(2, artifact.getName());
                statement.setString(3, artifact.getType().toString());
                statement.setInt(4, artifact.getAttack());
                statement.setInt(5, artifact.getDefense());
                statement.setInt(6, artifact.getHitPoints());
                statement.executeUpdate();
            }
        } finally {
            try{
                if(statement != null) statement.close();
                if(resultSet != null) resultSet.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static Hero loadHero(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Hero hero = null;
        try {
            connection = DriverManager.getConnection(databaseManager.getURL() + databaseManager.getDB_NAME(), databaseManager.getUSER(), databaseManager.getPASSWORD());
            String query = "SELECT * FROM heroes WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if(resultSet.next())
            {
                String name = resultSet.getString("name");
                HeroClass heroClass = HeroClass.valueOf(resultSet.getString("class"));
                int level = resultSet.getInt("level");
                int experience = resultSet.getInt("experience");
                int attack = resultSet.getInt("attack");
                int defense = resultSet.getInt("defense");
                int hitPoints = resultSet.getInt("hitPoints");
                String image = resultSet.getString("image");
                int hero_id = resultSet.getInt("id");

                hero = new Hero(name, heroClass);
                hero.setLevel(level);
                hero.setExperience(experience);
                hero.setAttack(attack);
                hero.setDefense(defense);
                hero.setHitPoints(hitPoints);
                hero.setImage(image);
                hero.setID(hero_id);

                query = "SELECT * FROM artifacts WHERE hero_id = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, id);
                resultSet = statement.executeQuery();
                while(resultSet.next())
                {
                    String artifactName = resultSet.getString("name");
                    ArtifactType type = ArtifactType.valueOf(resultSet.getString("type"));
                    int artifactAttack = resultSet.getInt("attack");
                    int artifactDefense = resultSet.getInt("defense");
                    int artifactHitPoints = resultSet.getInt("hitPoints");
                    Artifact artifact = new Artifact(artifactName, type, artifactAttack, artifactDefense, artifactHitPoints);
                    hero.equipArtifact(artifact);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hero;
    }

    public static List<Hero> getAllHeroes(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Hero> heroes = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(databaseManager.getURL() + databaseManager.getDB_NAME(), databaseManager.getUSER(), databaseManager.getPASSWORD());
            String query = "SELECT * FROM heroes";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while(resultSet.next())
            {
                int id = resultSet.getInt("id");
                Hero hero = new Hero(resultSet.getString("name"), HeroClass.valueOf(resultSet.getString("class")));
                hero.setLevel(resultSet.getInt("level"));
                hero.setExperience(resultSet.getInt("experience"));
                hero.setAttack(resultSet.getInt("attack"));
                hero.setDefense(resultSet.getInt("defense"));
                hero.setHitPoints(resultSet.getInt("hitPoints"));
                hero.setImage(resultSet.getString("image"));
                hero.setID(id);
                heroes.add(hero);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return heroes;
    }

    public boolean setExperience(int experience) {
        
        if (this.level >= 6) {
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

    public void setID(int id) {
        this.id = id;
    }
    
    public int getID() {
        return id;
    }
    
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public Artifact getWeapon() {
        return weapon;
    }
    
    public Artifact getArmor() {
        return armor;
    }
    
    public Artifact getHelm() {
        return helm;
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

    public void setAttack(int attack) {
        this.attack = attack;
    }
    
    public int getAttack() {
        return attack;
    }
    
    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
    
    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }
    
    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
    
    public int takeDamage(int damage) {
        life -= damage;
        return life;
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
        '}';
    }
}

