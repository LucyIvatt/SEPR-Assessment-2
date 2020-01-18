package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * The class which creates the Fortress within the level
 *
 * @author Jordan Spooner
 */
public class Fortress extends Unit {

    private float spawnRate;
    private ArrayList<Vector2> alienPositions = new ArrayList<>();

    public Fortress(Vector2 position, int width, int height, Texture texture, int maxHealth, float spawnRate, int level) {
        super(position, width, height, texture, maxHealth);
        this.spawnRate = spawnRate;

        if (level == 1) {
            alienPositions.add(new Vector2(22 * 32, 1044 - 3 * 32));
            alienPositions.add(new Vector2(33 * 32, 1044 - 3 * 32));
            alienPositions.add(new Vector2(24 * 32, 1044 - 6 * 32));
            alienPositions.add(new Vector2(31 * 32, 1044 - 6 * 32));
            alienPositions.add(new Vector2(28 * 32 - 16, 1044 - 8 * 32));
        }

        else if (level == 2) {
            alienPositions.add(new Vector2(33 + 37 * 32, 212 + 15 * 32));
            alienPositions.add(new Vector2(33 + 41 * 32, 212 + 15 * 32));
            alienPositions.add(new Vector2(33 + 33 * 32, 212 + 17 * 32));
            alienPositions.add(new Vector2(33 + 30 * 32, 212 + 20 * 32));
            alienPositions.add(new Vector2(33 + 30 * 32, 212 + 24 * 32));
            alienPositions.add(new Vector2(33 + 44 * 32, 212 + 20 * 32));
            alienPositions.add(new Vector2(33 + 44 * 32, 212 + 24 * 32));
        }

        else if (level == 3) {
            alienPositions.add(new Vector2(33 + 25 * 32, 212 + 17 * 32));
            alienPositions.add(new Vector2(33 + 29 * 32, 212 + 17 * 32));
            alienPositions.add(new Vector2(33 + 32 * 32, 212 + 19 * 32));
            alienPositions.add(new Vector2(33 + 22 * 32, 212 + 19 * 32));
            alienPositions.add(new Vector2(33 + 20 * 32, 212 + 21 * 32));
            alienPositions.add(new Vector2(33 + 34 * 32, 212 + 21 * 32));
            alienPositions.add(new Vector2(33 + 20 * 32, 212 + 24 * 32));
            alienPositions.add(new Vector2(33 + 34 * 32, 212 + 24 * 32));
        }
    }

    public float getSpawnRate() {
        return this.spawnRate;
    }

    public ArrayList<Vector2> getAlienPositions() {
        return alienPositions;
    }
}