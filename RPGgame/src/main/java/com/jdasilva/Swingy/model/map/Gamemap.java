package com.jdasilva.Swingy.model.map;

public class Gamemap {
    private int size;
    private char[][] map;
    private boolean visited[][];
    private int heroX;
    private int heroY;

    public Gamemap(int herolevel) {
        size = (herolevel - 1) * 5 + 10 - (herolevel % 2);
        map = new char[size][size];
        visited = new boolean[size][size];
        heroX = size / 2;
        heroY = size / 2;

        initializemap();
    }

    private void initializemap() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = 'X';
                visited[i][j] = false;
            }
        }
        map[heroY][heroX] = 'P';
        visited[heroY][heroX] = true;
    }

    public boolean moveHero(String dir) {
        map[heroY][heroX] = 'X';
        visited[heroY][heroX] = true;

        switch (dir.toLowerCase()) {
            case "n":
                if(heroY > 0){
                    heroY--;
                }else return false;
                break;
            case "e":
                if(heroX < size - 1){
                    heroX++;
                }else return false;
                break;
            case "s":
                if(heroY < size - 1){
                    heroY++;
                }else return false;
                break;
            case "w":
                if(heroX > 0){
                    heroX--;
                }else return false;
                break;
        }

        map[heroY][heroX] = 'P';
        visited[heroY][heroX] = true;
        return true;
    }

    public char[][] getMap() {
        return map;
    }

    public int getSize() {
        return size;
    }

    public boolean[][] getVisited() {
        return visited;
    }

    public void resetHeroPosition() {
        map[heroY][heroX] = 'X';
        visited[heroY][heroX] = false;
        heroX = size / 2;
        heroY = size / 2;
        map[heroY][heroX] = 'P';
        visited[heroY][heroX] = true;
    }

    public String getHeroPosition() {
        return "Hero position: " + heroX + ", " + heroY;
    }
}
