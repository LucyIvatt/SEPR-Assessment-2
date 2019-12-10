package com.mygdx.game.sprites;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Fortress extends Unit {

    private int spawnRate;

    private ArrayList<Vector2> alienPositions;

    public Fortress(Vector2 position, int width, int height, Texture texture, int maxHealth, ArrayList<Vector2> alienPositions) {
        super(position, width, height, texture, maxHealth);
        this.alienPositions = alienPositions;
    }

    public int getSpawnRate() {
        return this.spawnRate;
    }

    public void setSpawnRate(int spawnRate) {
        this.spawnRate = spawnRate;
    }

    public ArrayList<Vector2> getAlienPositions() {
        return alienPositions;
    }
}