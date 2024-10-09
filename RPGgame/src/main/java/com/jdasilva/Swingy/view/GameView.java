package com.jdasilva.Swingy.view;

import com.jdasilva.Swingy.model.hero.Artifact;
import com.jdasilva.Swingy.model.hero.Hero;

public interface GameView {
    void displayMap(char[][] map, boolean[][] visited);
    void gameOver();
    void win();
    void displayMessage(String message);
    public void showArtifactFound(Artifact artifact);
    public boolean wantstoCollectArtifact();
    public void equipArtifact(Artifact artifact);
    public void levelup(Hero hero); 
}
