package com.mygdx.game.sprites;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Alien extends Character {

    private Vector2[] waypoints;
    private int currentIndex;

    public Alien(Vector2 position, int width, int height, Texture texture, int maxHealth, int range, Unit target,
                 int speed, int dps, int bearing, Vector2[] wayPoints, float attackCooldown) {
        super(position, width, height, texture, maxHealth, range, target, speed, dps, attackCooldown);
        this.waypoints = wayPoints;
        currentIndex = 0;
    }

    public void update() {
        nextWayPoint();
        Vector2 newPos = moveAlongGrid(waypoints[currentIndex]);
        // Sets the position to the next position between way points
        setPosition(newPos.x, newPos.y);

    }

    // checks whether the alien is already at the next wayPoint if so sets the target wayPoint to the next in line
    // if the alien is at the end of the list of way points it will return to the starting way point
    private void nextWayPoint() {
        if (getPosition().y == waypoints[currentIndex].y && getPosition().x == waypoints[currentIndex].x) {
            // if alien has completed list of way points
            currentIndex++;
            if (currentIndex >= (waypoints.length)) {
                // set current way point to the first way point
                currentIndex = 0;
            }
        }
    }

    // Same as the above, but if the alien reaches the end of the list of way points it will move back through all
    // previous way points before going back to the starting way point

    // Movement at only 90 degree angles
    private Vector2 moveAlongGrid(Vector2 destination) { ;
        Vector2 nextPos = getPosition();
        // if the x movement is complete: move along the y axis
        if (getPosition().x == destination.x) {
            // if the destination is above the current position move up otherwise move down
            if (destination.y > getPosition().y) {
                nextPos.y += this.getSpeed();
            } else {
                nextPos.y -= this.getSpeed();
            }
        } else {
            // movement along the x axis
            if (destination.x > getPosition().x) {
                nextPos.x += this.getSpeed();
            } else {
                nextPos.x -= this.getSpeed();
            }
        }
        return nextPos;
    }

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
}


