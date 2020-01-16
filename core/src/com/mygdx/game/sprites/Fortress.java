package com.mygdx.game.sprites;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Fortress extends Unit {

    private float spawnRate;
    private ArrayList<Vector2> alienPositions;

    /**
     * The class which creates the Fortress within the level
     *
     * @author Jordan Spooner
     */

    public Fortress(Vector2 position, int width, int height, Texture texture, int maxHealth, ArrayList<Vector2> alienPositions, float spawnRate) {
        super(position, width, height, texture, maxHealth);
        this.alienPositions = alienPositions;
        this.spawnRate = spawnRate;
    }

    public float getSpawnRate() {
        return this.spawnRate;
    }

    public ArrayList<Vector2> getAlienPositions() {
        return alienPositions;
    }
}