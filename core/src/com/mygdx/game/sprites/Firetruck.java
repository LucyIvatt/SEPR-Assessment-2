package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Firetruck extends Character {

    private int max_water;
    private int current_water;
    private boolean selected;

    // Getters and Setters
    public int getMax_water() {
        return max_water;
    }

    public int getCurrent_water() {
        return current_water;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setCurrent_water(int current_water) {
        this.current_water = current_water;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    // Constructor
    public Firetruck(Vector2 position, int width, int height, Texture texture, int maxHealth, int range, Unit target,
                     int speed, int dps, int bearing, int max_water, boolean selected) {
        super(position, width, height,  texture, maxHealth, range, target, speed, dps);
        this.max_water = max_water;
        this.current_water = max_water;
        this.selected = selected;
    }

    // User attacks alien/fortress and firetruck water level decreases
    public void firetruckAttack(Unit target, int current_water) {
        if (this.current_water == 0) {
            //show message telling them they have ran out of water and to go refill to continue
        } else {
            setCurrent_water(current_water - 10);
            target.takeDamage(10);
        }
    }

    // Refill water level of firetruck
    public void refillWater() {
        this.current_water = max_water;
    }

    public void move(String direction) { //change from string to 1, 2, 3, 4
        if (direction == "right") {
            setPosition(getPosition().x + getSpeed(), getPosition().y);
        } else if (direction == "left") {
            setPosition(getPosition().x - getSpeed(), getPosition().y);
        } else if (direction == "up") {
            setPosition(getPosition().x, getPosition().y + getSpeed());
        } else if (direction == "down") {
            setPosition(getPosition().x, getPosition().y - getSpeed());
        }
    }

    public boolean willCollide(Entity other, String direction) {
        if (direction == "up") {
            if (getPosition().y > other.getTopRight().y || getTopRight().y + getSpeed() < getPosition().y) {
                return false;
            } else if (getPosition().x > other.getTopRight().x || getTopRight().x < other.getPosition().x) {
                return false;
            }
        } else if (direction == "down") {
            if (getPosition().y - getSpeed() > other.getTopRight().y || getTopRight().y < other.getPosition().y) {
                return false;
            } else if (getPosition().x > other.getTopRight().x || getTopRight().x < other.getPosition().x) {
                return false;
            }
        } else if (direction == "right") {
            if (getTopRight().x + getSpeed() < other.getPosition().x || getPosition().x > other.getTopRight().x) {
                return false;
            } else if (getPosition().y > other.getTopRight().y || getTopRight().y < other.getPosition().y) {
                return false;
            }
        } else if (direction == "left") {
            if (getPosition().x - getSpeed() > other.getTopRight().x || getTopRight().x < other.getPosition().x) {
                return false;
            } else if (getPosition().y > other.getTopRight().y || getTopRight().y < other.getPosition().y) {
                return false;
            }
        }
        return true;
    }
}