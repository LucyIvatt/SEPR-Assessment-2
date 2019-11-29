package com.mygdx.game.sprites;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.states.GameStateManager;

public class Alien extends Character {

    private Vector2[] wayPoints; // change to array list
    private int currentWP; //The current index of
    private long timeWhenLastMoved;
    private float timeInterval;
    private float timeWhenLastAttacked;

    public Alien(Vector2 position, int width, int height, int maxHealth, int range, Texture texture, Unit target,
                 int speed, int dps, int bearing, Vector2[] wayPoints) {
        super(position, width, height, maxHealth, range, texture, target, speed, dps);
        this.wayPoints = wayPoints;
        this.timeWhenLastMoved = 0;
        this.timeInterval = 0.05f;
        this.timeWhenLastAttacked = 0;
    }
    // is called each frame
    public void update(){
        moveTo();
        seeTarget();
        attackIfInRange();
    }

    // create a rectangle with a width and height of range * 2 - COLLISION DETECTION
    private void seeTarget(){
        Vector2[] squaresInSight = createHitBox();
        Firetruck[] allFireTrucks = getAllFireTrucks();
        boolean hasTarget = false;
        for (int i =0; i < allFireTrucks.length;i++){
            if (isInArea(getAllFireTrucks()[i].getPosition(), squaresInSight)){
                setTarget(getAllFireTrucks()[i]);
                hasTarget = true;
            }
        }
        if (!hasTarget){
            setTarget(null);
        }
    }

    // checks whether the alien is already at the next wayPoint if so sets the target wayPoint to the next in line
    private void nextWayPoint(){

        if (getPosition() == wayPoints[currentWP]){
            if (currentWP == ((wayPoints.length)-1)){
                currentWP = 0;
            }
            else {
                currentWP++;
            }
        }
    }


    // translation equations returns a new vector - make to run on 90 degree angles
    private Vector2 translate(Vector2 destination, Vector2 currentPos){
        Vector2 nextPos = new Vector2();
        float dx = destination.x-currentPos.x;
        float dy = destination.y -currentPos.y;
        double length = Math.sqrt(dx*dx+dy*dy);
        dx/=length;
        dy/=length;
        dx *= getSpeed();
        dy *= getSpeed();
        nextPos.x += dx;
        nextPos.y += dy;
        return nextPos;
    }

    // Moves the alien between the wayPoints
    private void moveTo(){

        if (System.currentTimeMillis() > timeWhenLastMoved + timeInterval){
            Vector2 newPos = translate(wayPoints[currentWP], getPosition());
            setPosition(    Math.round(newPos.x), Math.round(newPos.y));
            timeWhenLastMoved = System.currentTimeMillis();
        }
    }

    // Attacks target if a target is set
    private void attackIfInRange(){
        // if the target is not null: meaning that there is a target present
        if (getTarget() != null){
            // presumably 1000 here represent a second
            if (System.currentTimeMillis() > timeWhenLastAttacked + 1000) {
                timeWhenLastAttacked = System.currentTimeMillis();
                getTarget().takeDamage(getDps());
            }
        }
    }


}
