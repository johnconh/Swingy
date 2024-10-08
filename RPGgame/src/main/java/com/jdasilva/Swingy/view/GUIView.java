package com.jdasilva.Swingy.view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.jdasilva.Swingy.controller.GameControllerGUI;
import com.jdasilva.Swingy.model.enemy.Enemy;
import com.jdasilva.Swingy.model.hero.Hero;
import com.jdasilva.Swingy.model.hero.Artifact;

public class GUIView extends JFrame{
    private JLabel heroStatsLabel;
    private JLabel enemyStatsLabel;
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
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

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

        heroStatsLabel = new JLabel("Hero Stats", SwingConstants.CENTER);
        add(heroStatsLabel, BorderLayout.NORTH);

        setVisible(true);
    }

    public void updateMap(char[][] map, boolean[][] visited){
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                if(visited[i][j]){
                    mapLabels[i][j].setText("P");
                    mapLabels[i][j].setBackground(Color.GREEN);
                }else if (map[i][j] == 'X'){
                    mapLabels[i][j].setText("X");
                    mapLabels[i][j].setBackground(Color.RED);
                } else {
                    mapLabels[i][j].setText("X");
                    mapLabels[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }

    public void showEnemy(Enemy enemy){
        JOptionPane.showMessageDialog(this, "Enemy: " + enemy.getName());
    }

    public String getBattleAction(){
        Object[] options = {"Fight", "Run"};
        int choice = JOptionPane.showOptionDialog(this, "Choose your action", "Battle", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        return (choice == 0) ? "Fight" : "Run";
    }

    public void displayMessage(String message){
        JOptionPane.showMessageDialog(this, message);
    }

    public void heroLife(Hero hero){
        heroStatsLabel.setText("Hero Life: " + hero.getLife());
    }

    public void enemyLife(Enemy enemy){
        enemyStatsLabel = new JLabel("Enemy Stats", SwingConstants.CENTER);
        enemyStatsLabel.setText("Enemy Life: " + enemy.getHitPoints());
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

    public void heroStats(Hero hero){
        JOptionPane.showMessageDialog(this, hero.toString());
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
