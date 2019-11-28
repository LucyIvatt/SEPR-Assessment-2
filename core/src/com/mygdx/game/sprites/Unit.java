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

    public boolean inRange(Unit unit) {
        int posX = this.position.x;
        int posY = this.position.y;

        for (int i=posX-this.range; i<=posX+this.range; i++) {
            for (int j=posY-this.range; j<=posY+this.range; j++) {
                Vector3 tempPos = new Vector3(i,j,0);

                if (this.position == unit.position) {
                    return true;
                }
            }
        }

        return false;
    }
}