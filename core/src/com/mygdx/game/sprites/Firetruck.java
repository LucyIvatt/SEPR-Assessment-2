package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * The class which creates a firetruck object to be controlled by the user within the PlayState.
 *
 * @author Cassie Lillystone
 */

public class Firetruck extends Character {

    private int maxWater;
    private int currentWater;
    private boolean selected;

    public Firetruck(Vector2 position, int width, int height, Texture texture, int maxHealth, int range, Unit target,
                     int speed, int dps, int maxWater, boolean selected) {
        super(position, width, height, texture, maxHealth, range, target, speed, dps);
        this.maxWater = maxWater;
        this.currentWater = maxWater;
        this.selected = selected;
    }

    /**
     * A method which controllers Firetruck movement depending on the direction input
     * @param direction 1 = Left, 2 = Right, 3 = Up, 4 = Down
     */
    public void move(int direction) { // 1, 2, 3, 4 --> Left, Right, Up, Down
        float deltaTime = Gdx.graphics.getDeltaTime();
        if (direction == 2) {
            setPosition(getPosition().x + getSpeed() * deltaTime, getPosition().y);
        } else if (direction == 1) {
            setPosition(getPosition().x - getSpeed() * deltaTime, getPosition().y);
        } else if (direction == 3) {
            setPosition(getPosition().x, getPosition().y + getSpeed() * deltaTime);
        } else if (direction == 4) {
            setPosition(getPosition().x, getPosition().y - getSpeed() * deltaTime);
        }
    }

    /**
     * A method which checks if the Firetruck will collide with the input object if it moves on the input direction
     * next game tick.
     * @param other the object that the collision will be checked for
     * @param direction 1 = Left, 2 = Right, 3 = Up, 4 = Down
     * @return true if the objects will collide, otherwise false
     */
    public boolean willCollide(Entity other, int direction) {

        if (direction == 1) { // left
            if (getPosition().x - getSpeed() * Gdx.graphics.getDeltaTime() >= other.getTopRight().x || getTopRight().x <= other.getPosition().x ||
                    getPosition().y >= other.getTopRight().y || getTopRight().y <= other.getPosition().y) {
                return false;
            }
        }

        else if (direction == 2) { // right

            if ((getTopRight().x + getSpeed() * Gdx.graphics.getDeltaTime() <= other.getPosition().x || getPosition().x >= other.getTopRight().x ||
                    getPosition().y >= other.getTopRight().y || getTopRight().y <= other.getPosition().y)) {
                return false;
            }
        }

        else if (direction == 3) { // up
            if (getPosition().y >= other.getTopRight().y || getTopRight().y + getSpeed() * Gdx.graphics.getDeltaTime()<= other.getPosition().y ||
                    getPosition().x >= other.getTopRight().x || getTopRight().x <= other.getPosition().x) {
                return false;
            }
        }

        else if (direction == 4) { // down
            if (getPosition().y - getSpeed() * Gdx.graphics.getDeltaTime() >= other.getTopRight().y || getTopRight().y <= other.getPosition().y ||
                    getPosition().x >= other.getTopRight().x || getTopRight().x <= other.getPosition().x) {
                return false;
            }
        }
        return true;
    }

    public int getMaxWater() {
        return maxWater;
    }

    public int getCurrentWater() {
        return currentWater;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setCurrentWater(int currentWater) {
        this.currentWater = currentWater;
    }

    public void updateCurrentWater(int waterUsed) {
        if ((this.currentWater - waterUsed) < 0) {
            this.currentWater = 0;
            return;
        }
        this.currentWater -=  waterUsed;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }



}