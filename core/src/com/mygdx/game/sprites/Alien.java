package com.mygdx.game.sprites;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Alien extends Character {

    private Vector2[] wayPoints; // change to array list
    private int currentWP; //The current index of
    private float timeWhenLastAttacked; // The time since the Alien last attacked
    private boolean forwards = true; // determines if the alien is moving forwards...
    // ... through the way points list or backwards
    private boolean nextWayPointVersion1 = true; // Used for TESTING ONLY, there are currently two versions..
    // ..for what happens when the alien reaches the final way point in the list, this boolean decides which..
    // .. version is followed

    public Alien(Vector2 position, int width, int height, int maxHealth, int range, Texture texture, Unit target,
                 int speed, int dps, int bearing, Vector2[] wayPoints) {
        super(position, width, height, maxHealth, range, texture, target, speed, dps);
        this.wayPoints = wayPoints;
        this.timeWhenLastAttacked = 0;
    }
    // is called each frame
  public void update(){
       moveTo();
        attackIfHasTarget();
    }

    // Moves the alien between the wayPoints
    private void moveTo(){
        // checks which wayPoint version we are using
        if (nextWayPointVersion1) {
            nextWayPoint(); // checks if a next way point is needed
        } else {
            nextWayPointAlt();
        }
        // Gets the next translation point
        Vector2 newPos = translateInGridMovement(getPosition(), wayPoints[currentWP]);
        // Sets the position to the next position between way points
        setPosition(Math.round(newPos.x), Math.round(newPos.y));
    }

    // checks whether the alien is already at the next wayPoint if so sets the target wayPoint to the next in line
    // if the alien is at the end of the list of way points it will return to the starting way point
    private void nextWayPoint(){

        if (getPosition() == wayPoints[currentWP]){
            // if alien has completed list of way points
            if (currentWP == ((wayPoints.length)-1)){
                // set current way point to the first way point
                currentWP = 0;
            }
            else {
                currentWP++;
            }
        }
    }

    // Same as the above, but if the alien reaches the end of the list of way points it will move back through all
    // previous way points before going back to the starting way point
    private void nextWayPointAlt(){
        if (getPosition() == wayPoints[currentWP]){
            // if alien has completed list of way points
            if (currentWP == ((wayPoints.length)-1) || currentWP == 0){
                // Make the alien move the opposite way through way points list
                forwards = !forwards;
            }
            if (forwards){
                // if going forwards through way points list increment the way points index by 1
                currentWP++;
            } else{
                // else decrement index by 1
                currentWP --;
            }
        }
    }

    // Movement at only 90 degree angles
    private Vector2 translateInGridMovement(Vector2 currentPos, Vector2 destination){
        Vector2 nextPos = new Vector2();
        // if the x movement is complete: move along the y axis
        if (currentPos.x == destination.x){
            // if the destination is above the current position move up otherwise move down
            if (destination.y > currentPos.y){
                nextPos.y += getSpeed();
            } else{
                nextPos.y -= getSpeed();
            }
        } else {
            // movement along the x axis
            if (destination.x > currentPos.x){
                nextPos.x += getSpeed();
            } else{
                nextPos.x -= getSpeed();
            }
        }
        return nextPos;
    }

    // currently unused function, would move the enemy between points at angles rather than just 90 degree movement
    // Would prefer if you did not delete this in case we need it in the future!!!!
    private Vector2 translate(Vector2 destination, Vector2 currentPos){
        Vector2 nextPos = new Vector2();
        // nX and nY (nextY, nextX )represent the increase needed for the current pos to translate towards the destination
        float nX = destination.x-currentPos.x;
        float nY = destination.y -currentPos.y;
        // Literally just Pythag to get the length of the line between the currentPos and the destination
        double length = Math.sqrt(nX*nX+nY*nY);
        // Divide both nX and nY by the length to make both the increase and x and y consistent with one another
        nX/=length;
        nY/=length;
        // Multiply by speed to control the increase
        nX *= getSpeed();
        nY *= getSpeed();
        // add nX and nY to the new x and y position for the alien
        nextPos.x += nX;
        nextPos.y += nY;
        return nextPos;
    }

    // Attacks target if a target is set
    private void attackIfHasTarget(){
        // if the target is not null: meaning that there is a target present
        if (getTarget() != null){
            // Attacks every second instead of every update: checks if the system clock is greater than the time when
            // the alien last attacks plus one second, presuming 1000 represent a second
            if (System.currentTimeMillis() > timeWhenLastAttacked + 1000) {
                // sets the timeWhenLast attacked to the current time to restart timer
                timeWhenLastAttacked = System.currentTimeMillis();
                // gets the target and calls the take damage function on that unit with an damage value of dps
                getTarget().takeDamage(getDps());
            }
        }
    }

}
