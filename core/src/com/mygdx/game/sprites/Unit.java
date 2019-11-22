package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public abstract class Unit extends TestEntity { //needs to be changed to extend entity
    private int maxHealth;
    private int currentHealth;
    private int range;
    
    private Texture texture;
    
    public Unit() { // Default constructor for easy testing
    	super();
    	this.maxHealth = 100;
        this.currentHealth = 100;
        this.range = 5;
    }

    public Unit(Vector3 position, int width, int height) { // Default constructor
        super(position, width, height);
        this.maxHealth = 100;
        this.currentHealth = 100;
        this.range = 5;
    }
    
    public Unit(Vector3 position, int width, int height, int maxHealth) { 
        super(position, width, height);
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.range = 5;
    }
    
    public Unit(Vector3 position, int width, int height, int maxHealth, int range) { 
        super(position, width, height);
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
    
    public void takeDamage() {
    	
    }
}
