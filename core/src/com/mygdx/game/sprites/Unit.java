package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public abstract class Unit extends Entity {
    private int maxHealth;
    private int currentHealth;

    // Default constructor
    public Unit( Vector2 position, int width, int height, Texture texture) {
        super(position, width, height, texture);
        this.maxHealth = 100;
        this.currentHealth = 100;
    }

    public Unit( Vector2 position, int width, int height, Texture texture, int maxHealth) {
        super(position, width, height, texture);
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
    }


    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int newHealth) {
        //Ensuring no negative numbers or over
        if(newHealth >= 0 && newHealth <= maxHealth){
            this.currentHealth = newHealth;
        } else if (newHealth > maxHealth){
            this.currentHealth = maxHealth;
        } else {
            this.currentHealth = 0;
        }
    }

    public boolean isDead() {
        if(this.currentHealth == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void addHealth(int heal){
            int newHealth = getCurrentHealth() + Math.abs(heal);
            if (newHealth > maxHealth) {
                newHealth = maxHealth;
            }
            setCurrentHealth(newHealth);
        }


    public void takeDamage(int damage) {
        int newHealth = getCurrentHealth() - damage;
        if (!isDead()) {
            setCurrentHealth(newHealth);
        }
    }
}