package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public abstract class Character extends Unit {
    private Unit target;
    private int speed;
    private int dps;
    private float attackCooldown;
    private float timeSinceAttack;
    private int range;

    //Thinking the standard constructor should not force a target since the firetruck will
    //spawn with no enemies in target.
    // Constructor
    public Character(Vector2 position, int width, int height, Texture texture, int maxHealth, int range, Unit target,
                     int speed, int dps, float attackCooldown) {
        super(position, width, height, texture, maxHealth, range);
        this.target = target;
        this.speed = speed;
        this.dps = dps;
        this.attackCooldown = attackCooldown;
        this.timeSinceAttack = 0;
        this.range = range;
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

    public void setSpeed(int speed){
        this.speed = speed;
    }

    public void setDps(int dps){
        this.dps = dps;
    }

    //Maybe don't need it in the future...?
    public void setTarget(Unit target) {
        this.target = target;
    }

    public float getTimeSinceAttack() {
        return timeSinceAttack;
    }

    public void resetTimeSinceAttack() {
        this.timeSinceAttack = 0;
    }

    public void updateTimeSinceAttack(float dt) {
        this.timeSinceAttack += dt;
    }

    public float getAttackCooldown() {
        return attackCooldown;
    }

    public int getRange() {
        return range;
    }
}
