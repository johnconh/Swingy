package com.jdasilva.Swingy.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.jdasilva.Swingy.controller.GameControllerGUI;
import com.jdasilva.Swingy.model.enemy.Enemy;
import com.jdasilva.Swingy.model.hero.Hero;
import com.jdasilva.Swingy.model.hero.Artifact;
import java.net.URL;

public class GUIView extends JFrame{
    private JLabel heroStatsLabel, enemyStatsLabel;
    private JLabel heroImageLabel, enemyImageLabel;
    private JProgressBar heroLifeBar, enemyLifeBar;
    private JPanel enemyPanel;
    private JLabel[][] mapLabels;
    private JPanel mapPanel;
    private JButton northButton, eastButton, southButton, westButton;
    private GameControllerGUI controller;
    private String direction;
    private final Object lock = new Object();

    public GUIView(GameControllerGUI controller){
        this.controller = controller;
        initGUI();
    }

    private void initGUI()
    {
        setTitle("Swingy - GUI");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel heroPanel = new JPanel();
        heroPanel.setLayout(new BoxLayout(heroPanel, BoxLayout.Y_AXIS));
        heroImageLabel = new JLabel();
        heroLifeBar = new JProgressBar();
        heroLifeBar.setStringPainted(true);
        heroLifeBar.setForeground(Color.GREEN);
        heroLifeBar.setBackground(Color.RED);
        heroStatsLabel = new JLabel();
        heroPanel.add(heroImageLabel);
        heroPanel.add(heroLifeBar);
        heroPanel.add(heroStatsLabel);
        add(heroPanel, BorderLayout.WEST);
        heroPanel.revalidate();
        heroPanel.repaint();

        enemyPanel = new JPanel();
        enemyPanel.setLayout(new BoxLayout(enemyPanel, BoxLayout.Y_AXIS));
        enemyImageLabel = new JLabel();
        enemyLifeBar = new JProgressBar();
        enemyLifeBar.setStringPainted(true);
        enemyLifeBar.setForeground(Color.GREEN);
        enemyLifeBar.setBackground(Color.RED);
        enemyStatsLabel = new JLabel();
        enemyPanel.add(enemyImageLabel);
        enemyPanel.add(enemyLifeBar);
        enemyPanel.add(enemyStatsLabel);
        enemyPanel.setVisible(false);
        add(enemyPanel, BorderLayout.EAST);

        mapPanel = new JPanel();
        mapPanel.setLayout(new GridLayout(controller.getSizeMap(), controller.getSizeMap()));
        mapLabels = new JLabel[controller.getSizeMap()][controller.getSizeMap()];

        for (int i = 0; i < controller.getSizeMap(); i++) {
            for (int j = 0; j < controller.getSizeMap(); j++) {
                mapLabels[i][j] = new JLabel("", SwingConstants.CENTER);
                mapLabels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                mapPanel.add(mapLabels[i][j]);
            }
        }

        add(mapPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(3, 3));
        northButton = new JButton("North");
        eastButton = new JButton("East");
        southButton = new JButton("South");
        westButton = new JButton("West");

        northButton.addActionListener(new DirectionListener("N"));
        eastButton.addActionListener(new DirectionListener("E"));
        southButton.addActionListener(new DirectionListener("S"));
        westButton.addActionListener(new DirectionListener("W"));

        controlPanel.add(new JLabel());
        controlPanel.add(northButton); 
        controlPanel.add(new JLabel()); 
        controlPanel.add(westButton); 
        controlPanel.add(new JLabel()); 
        controlPanel.add(eastButton);
        controlPanel.add(new JLabel());
        controlPanel.add(southButton); 
        add(controlPanel, BorderLayout.SOUTH);

        heroStatsLabel = new JLabel();
        add(heroStatsLabel, BorderLayout.NORTH);

        setVisible(true);
    }

    public void updateMap(char[][] map, boolean[][] visited){
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                if(map[i][j] == 'P'){
                    mapLabels[i][j].setText("P");
                    mapLabels[i][j].setBackground(Color.GREEN);
                }else if (visited[i][j]){
                    mapLabels[i][j].setText("X");
                    mapLabels[i][j].setBackground(Color.RED);
                } else {
                    mapLabels[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }
    
    public void initializeHero(Hero hero){
        setHeroImage(hero.getImage());
        updateHeroLifeBar(hero.getLife(), hero.getHitPoints());
        updateHeroStats(hero);
    }

    public void initializeEnemy(Enemy enemy){
        setEnemyImage(enemy.getImage());
        updateEnemyLifeBar(enemy.getLife(), enemy.getHitPoints());
        updateEnemyStats(enemy);
        enemyPanel.setVisible(true);
    }

    public void hideEnemyPanel(){
        enemyPanel.setVisible(false);
    }

    public void setHeroImage(String path){
        URL imageURL = getClass().getResource(path);
        if(imageURL == null){
            System.err.println("Image not found " + path);
            return;
        }

        ImageIcon icon = new ImageIcon(imageURL);
        Image img = icon.getImage();
        int width = img.getWidth(null);
        int height = img.getHeight(null);
        Image newImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        icon = new ImageIcon(newImg);
        heroImageLabel.setIcon(icon);
        enemyImageLabel.setPreferredSize(new Dimension(width, height));
        enemyImageLabel.setMinimumSize(new Dimension(width, height));
        heroImageLabel.revalidate();
        heroImageLabel.repaint();
    }
    
    public void setEnemyImage(String path){
        URL imgURL = getClass().getResource(path);
        if (imgURL == null) {
            System.err.println("Couldn't find file: " + path);
            return;
        }

        ImageIcon icon = new ImageIcon(imgURL);
        Image img = icon.getImage();
        int width = img.getWidth(null);
        int height = img.getHeight(null);
        Image newImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        icon = new ImageIcon(newImg);
        enemyImageLabel.setIcon(icon);
        enemyImageLabel.setPreferredSize(new Dimension(width, height));
        enemyImageLabel.setMinimumSize(new Dimension(width, height));
        enemyImageLabel.revalidate();
        enemyImageLabel.repaint();
    }

    public void updateHeroLifeBar(int life, int maxLife){
        int health = (int)((life / (double)maxLife) * 100);
        heroLifeBar.setValue(health);
    }

    public void updateEnemyLifeBar(int life, int maxLife){
        int health = (int)((life / (double)maxLife) * 100);
        enemyLifeBar.setValue(health);
    }

    public void updateHeroStats(Hero hero){
        String stats = "<html>" 
                        + hero.getName() + " Lvl: " + hero.getLevel() + "<br>"
                        + "Attack: " + hero.getAttack() + "<br>"
                        + "Defense: " + hero.getDefense() + "<br>"
                        + "Helm: " + hero.getHelm() + "<br>"
                        + "Armor: " + hero.getArmor() + "<br>"
                        + "Weapon: " + hero.getWeapon() + "<html>";

        heroStatsLabel.setText(stats);
    }

    public void updateEnemyStats(Enemy enemy){
        enemyStatsLabel.setText(enemy.getName());
    }
    
    public String getBattleAction(){
        Object[] options = {"Fight", "Run"};
        int choice = JOptionPane.showOptionDialog(this, "Choose your action", "Battle", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        return (choice == 0) ? "Fight" : "Run";
    }

    public void displayMessage(String message){
        JOptionPane.showMessageDialog(this, message);
    }

    public void gameOver(){
        JOptionPane.showMessageDialog(this, "Game Over");
    }

    public void win(){
        JOptionPane.showMessageDialog(this, "You win!");
    }

    public void showArtifactFound(Artifact artifact){
        JOptionPane.showMessageDialog(this, "Artifact found: " + artifact.getName());
    }

    public boolean wantstoCollectArtifact(){
        int choice = JOptionPane.showConfirmDialog(this, "Do you want to collect the artifact?", "Artifact found", JOptionPane.YES_NO_OPTION);
        return choice == JOptionPane.YES_OPTION;
    }

    public void levelup(Hero hero) {
        String message = "Congratulations! You have leveled up to level " + hero.getLevel() + "\n"
                       + "Your stats have been increased!\n"
                       + "Attack: " + hero.getAttack() + "\n"
                       + "Defense: " + hero.getDefense() + "\n"
                       + "Hit Points: " + hero.getHitPoints();
        
        JOptionPane.showMessageDialog(this, message, "Level Up!", JOptionPane.INFORMATION_MESSAGE);
    }

    public void equipArtifact(Artifact artifact){
        JOptionPane.showMessageDialog(this, "You have equipped " + artifact.getName());
    }

    public String getDirection(){
        synchronized(lock){
            while (direction == null){
                try{
                    lock.wait();
                }catch(InterruptedException e){
                    Thread.currentThread().interrupt();
                    return null;
                }
            }
            String dir = direction;
            direction = null;
            return dir;
        }
    }

    public void setDirection(String direction){
        synchronized(lock){
            this.direction = direction;
            lock.notifyAll();
        }
    }

    private class DirectionListener implements ActionListener{
        private String direction;

        public DirectionListener(String direction){
            this.direction = direction;
        }

        @Override
        public void actionPerformed(ActionEvent e){
            setDirection(direction);
        }
    }
}
