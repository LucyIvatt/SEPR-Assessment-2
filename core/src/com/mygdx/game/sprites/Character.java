package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public abstract class Character extends Unit {
    private Unit target;
    private int speed;
    private int dps;
    private int bearing;

    public Character(Vector2 position, int width, int height, int maxHealth, int range, Texture texture, Unit target, int speed, int dps, int bearing) {
        super(position, width, height, maxHealth, range, texture);
        this.target = target;
        this.speed = speed;
        this.dps = dps;
        this.bearing = bearing;
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

    public int getBearing() {
        return bearing;
    }

    public void setTarget(Unit target) {
        this.target = target;
    }

    public void setBearing(int bearing) {
        this.bearing = bearing;
    }


}
