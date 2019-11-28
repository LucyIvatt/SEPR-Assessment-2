package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public abstract class Unit extends Entity {
    private int maxHealth;
    private int currentHealth;
    private int range;
    
    private Texture texture;

    public Unit(Vector2 position, int width, int height, Texture texture) { // Default constructor
        super(width, height, texture, position);
        this.maxHealth = 100;
        this.currentHealth = 100;
        this.range = 5;
    }
    
    public Unit(int width, int height, Texture texture, Vector2 position, int maxHealth) {
        super(width, height, texture, position);
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.range = 5;
    }
    
    public Unit(Vector2 position, int width, int height, int maxHealth, int range, Texture texture) {
        super(width, height, texture, position);
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.range = range;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getRange() {
        return range;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public boolean isDead() {
        if(this.currentHealth == 0) {
            return true;
        }
        
        return false;
    }

    public void healHealth(int heal){
        int newHealth = getCurrentHealth() + heal;
        if (newHealth > maxHealth) {
            newHealth = maxHealth;
        }
        setCurrentHealth(newHealth);
    }
    
    public void takeDamage(int damage) {
        int newHealth = getCurrentHealth() - damage;
        if (isDead()) {
            // don't know how we're dealing with this atm
        } else {
            setCurrentHealth(newHealth);
        }
    }
}
