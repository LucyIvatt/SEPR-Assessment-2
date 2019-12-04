package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public abstract class Character extends Unit {
    private Unit target;
    private int speed;
    private int dps;

    //Thinking the standard constructor should not force a target since the firetruck will
    //spawn with no enemies in target.
    // Constructor
    public Character(Vector2 position, int width, int height, Texture texture, int maxHealth, int range, Unit target,
                     int speed, int dps) {
        super(position, width, height, texture, maxHealth, range);
        this.target = target;
        this.speed = speed;
        this.dps = dps;
    }

    // Getters and Setters
    public Unit getTarget() {
        return target;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDps() {
        return dps;
    }

    //Will we allow for targets outside of the range?
    public void setTarget(Unit target) {
        this.target = target;
    }

}
