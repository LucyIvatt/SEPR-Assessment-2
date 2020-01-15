package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public abstract class Character extends Unit {
    private Unit target;
    private int speed;
    private int damage;
    private int range;
    private float attackCooldown;
    private float timeSinceAttack;


    public Character(Vector2 position, int width, int height, Texture texture, int maxHealth, int range, Unit target,
                     int speed, int damage, float attackCooldown) {
        super(position, width, height, texture, maxHealth);
        this.target = target;
        this.speed = speed;
        this.damage = damage;
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

    public int getDamage() {
        return damage;
    }

    public void setTarget(Unit target) {
        this.target = target;
    }

    public float getTimeSinceAttack() {
        return timeSinceAttack;
    }

    public void resetTimeSinceAttack() {
        this.timeSinceAttack = 0;
    }

    public void updateTimeSinceAttack(float deltaTime) {
        this.timeSinceAttack += deltaTime;
    }

    public float getAttackCooldown() {
        return attackCooldown;
    }

    public int getRange() {
        return range;
    }
}
