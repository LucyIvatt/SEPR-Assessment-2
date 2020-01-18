package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * The Button class is used to create button objects for use on the MenuScreen
 * which can be used to change the current game State
 *
 * @author Alasdair Pilmore-Bedford and Lucy Ivatt
 */

public class Alien extends Character {

    private Vector2[] waypoints;
    private int currentIndex;
    private float attackCooldown; // The time the Character must wait before being able to attack again
    private float timeSinceAttack; // The time that has passed since the Character last attacked

    public Alien(Vector2 position, int width, int height, Texture texture, int maxHealth, int range, Unit target,
                 int speed, int dps, Vector2[] wayPoints, float attackCooldown) {
        super(position, width, height, texture, maxHealth, range, target, speed, dps);
        this.waypoints = wayPoints;
        currentIndex = 0;
        this.attackCooldown = attackCooldown;
        this.timeSinceAttack = 0;
    }

    /**
     * A method which is ran each tick and updates the aliens attributes accordingly.
     */
    public void update() {
        nextWayPoint();
        Vector2 newPos = moveAlongGrid(waypoints[currentIndex]);
        setPosition(newPos.x, newPos.y);
    }

    /**
     * A method which checks if the alien is as its next waypoint, if so it will set the target waypoint to the next
     * in the array. If the alien is at the end of the array, it will return to the starting waypoint.
     */
    private void nextWayPoint() {
        if (getPosition().y == waypoints[currentIndex].y && getPosition().x == waypoints[currentIndex].x) {
            currentIndex++;
            if (currentIndex >= (waypoints.length)) {
                currentIndex = 0;
            }
        }
    }

    /**
     * The method which moves the alien towards its destination vector
     * @param destination the Vector2 object specifying the aliens direction
     */
    private Vector2 moveAlongGrid(Vector2 destination) { ;
        Vector2 nextPos = getPosition();
        if (getPosition().x == destination.x) {
            if (destination.y > getPosition().y) {
                nextPos.y += this.getSpeed();
            } else {
                nextPos.y -= this.getSpeed();
            }
        } else {
            if (destination.x > getPosition().x) {
                nextPos.x += this.getSpeed();
            } else {
                nextPos.x -= this.getSpeed();
            }
        }
        return nextPos;
    }

    /**
     * The method which checks if a firetruck is in range and sets the aliens target accordingly
     * @param firetrucks the ArrayList containing the active firetrucks in the level
     */
    public void truckInRange(ArrayList<Firetruck> firetrucks) {
        if (this.hasTarget() && getTarget().getCurrentHealth() == 0) {
            setTarget(null);
        }
        for (Firetruck firetruck : firetrucks) {
            if (getTopRight().y + getRange() < firetruck.getPosition().y || getPosition().y - getRange() > firetruck.getTopRight().y ||
                    getTopRight().x + getRange() < firetruck.getPosition().x || getPosition().x - getRange() > firetruck.getTopRight().x) {
                if (getTarget() == firetruck) {
                    setTarget(null);
                }
            }
            else {
                if (getTarget() == null || firetruck.getCurrentHealth() < getTarget().getCurrentHealth()) {
                    setTarget(firetruck);
                }
            }
        }
    }

    public boolean hasTarget() {
        if (getTarget() == null) {
            return false;
        } else {
            return true;
        }
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
}


