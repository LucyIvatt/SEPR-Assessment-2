package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * The abstract class containing the shared methods and attributes used by the Firetruck and Alien class.
 *
 * @author Cassie Lillystone
 */

public abstract class Character extends Unit {

    private Unit target;
    private int speed;
    private int damage;
    private int range;


    public Character(Vector2 position, int width, int height, Texture texture, int maxHealth, int range, Unit target,
                     int speed, int damage) {
        super(position, width, height, texture, maxHealth);
        this.target = target;
        this.speed = speed;
        this.damage = damage;
        this.range = range;
    }

    public Unit getTarget() {
        return target;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDamage() {
        return damage;
    }

    public void setTarget(Unit target) {
        this.target = target;
    }

    public int getRange() {
        return range;
    }
}
