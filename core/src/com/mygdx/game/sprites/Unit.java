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

    // hit boxes are arrays with length 4, where each vector3 represents the corners of the hitbox
    public Vector3[] createHitBox(){
        Vector3[] hitBox = new Vector3[4];
        Vector3 currentSquare = getPosition();
        int range = getRange();
        //TL
        hitBox[0].x = currentSquare.x - range;
        hitBox[0].y = currentSquare.y + range;
        //TR
        hitBox[1].x = currentSquare.x + range;
        hitBox[1].y = currentSquare.y + range;
        //BL
        hitBox[2].x = currentSquare.x - range;
        hitBox[2].y = currentSquare.y - range;
        //BR
        hitBox[3].x = currentSquare.x + range;
        hitBox[3].y = currentSquare.y - range;
        return hitBox;
    }

    // checks if a fireTruck is in the search area can be extended to check if walls are in front of firetrucks
    public boolean isInArea(Vector3 fireTruck, Vector3[] location){
        if (fireTruck.x <= location[1].x && fireTruck.x >= location[0].x && fireTruck.y <= location[0].y && fireTruck.y >= location[2].y){
            return true;
        } else
            return false;
    }

}