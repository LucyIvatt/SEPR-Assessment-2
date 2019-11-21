package com.mygdx.game.sprites;

import java.util.ArrayList;

public class Fortress extends Unit {

    private int spawnRate;
    private String fortressImg;

    public Fortress(int posX, int posY, int maxHealth, int currentHealth, int range, int spawnRate, String img) {
        // (posX, posY, width, height, active, maxHealth, currentHealth, range)
        super(); // expecting unit to take these arguments for its constructor
        this.spawnRate = spawnRate;
        this.fortressImg = img;
    }

    public int getSpawnRate() {
        return this.spawnRate;
    }

    public void setSpawnRate(int new_spawnRate) {
        this.spawnRate = new_spawnRate;
    }

    public void produceAlien(int spawnRate) {
        //ArrayList<Object> aliens = new ArrayList<Object>(); // use a get method to get list of current aliens on map

        //for (int i=0; i<this.spawnRate; i++) {
        //    aliens.add(Alien()); // would be an alien
        //}
    }
}
