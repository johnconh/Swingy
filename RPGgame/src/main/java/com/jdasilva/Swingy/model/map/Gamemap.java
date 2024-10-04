package com.jdasilva.Swingy.model.map;

public class Gamemap {
    private int size;
    private char[][] map;
    private int heroX;
    private int heroY;

    public Gamemap(int herolevel) {
        size = (herolevel - 1) * 5 + 10 - (herolevel % 2);
        map = new char[size][size];
        heroX = size / 2;
        heroY = size / 2;

        initializemap();
    }

    private void initializemap() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = 'X';
            }
        }
        map[heroY][heroX] = 'P';
    }

    public boolean moveHero(String dir) {

        map[heroY][heroX] = 'X';

        switch (dir.toLowerCase()) {
            case "n":
                if(heroY > 0){
                    heroY--;
                }else
                {
                    return true;
                }
                break;
            case "e":
                if(heroX < size - 1){
                    heroX++;
                }else
                {
                    return true;
                }
                break;
            case "s":
                if(heroY < size - 1){
                    heroY++;
                }else
                {
                    return true;
                }
                break;
            case "w":
                if(heroX > 0){
                    heroX--;
                }else
                {
                    return true;
                }
                break;
        }

        map[heroY][heroX] = 'P';
        return false;
    }

    public char[][] getMap() {
        return map;
    }

    public String getHeroPosition() {
        return "Hero position: " + heroX + ", " + heroY;
    }
}
