package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * The abstract class containing the shared methods and attributes used by the Firetruck and Alien class.
 *
 * @author Jordan Spooner and Alasdair Pilmore-Bedford
 */

public abstract class Unit extends Entity {

    private int maxHealth;
    private int currentHealth;

    public Unit(Vector2 position, int width, int height, Texture texture, int maxHealth) {
        super(position, width, height, texture);
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
    }

    /**
     * Checks if the currentHealth equals 0
     * @return if the Unit has no health remaining then returns true, otherwise return false
     */
    public boolean isDead() {
        if(this.currentHealth <= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the new health value is between 0 and the Units maxHealth, if so sets currentHealth to this value.
     * If negative sets currentHealth to 0 and if greater than maxHealth sets currentHealth to maxHealth.
     * @param newHealth value to set currentHealth to
     */
    public void setCurrentHealth(int newHealth) {
        if(newHealth >= 0 && newHealth <= maxHealth){
            this.currentHealth = newHealth;
        }
        else if (newHealth > maxHealth){
            this.currentHealth = maxHealth;
        }
        else {
            this.currentHealth = 0;
        }
    }

    /**
     * Adds a value to currentHealth while preventing currentHealth from being greater than maxHealth
     * @param amount integer value to add to currentHealth
     */
    public void addHealth(int amount){
        int newHealth = getCurrentHealth() + Math.abs(amount);
        if (newHealth > maxHealth) {
            newHealth = maxHealth;
        }
        setCurrentHealth(newHealth);
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    /**
     * Adds subtracts the value from currentHealth while preventing currentHealth from being less than 0
     * @param damage integer value to remove from currentHealth
     */
    public void takeDamage(int damage) {
        int newHealth = getCurrentHealth() - damage;
        if (!isDead()) {
            setCurrentHealth(newHealth);
        }
    }
}