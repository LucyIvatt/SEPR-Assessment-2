package com.mygdx.game.sprites;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.states.GameStateManager;

public class Alien extends Character {

    private Vector2[] wayPoints;
    private int currentWP; //The current index of
    private long timeWhenLastMoved = 0;
    private float timeInterval = 0.05f;
    private float timeWhenLastAttacked = 0;

    public Alien (Vector2[] wayPoints, Unit target, int speed, int dps, int bearing){
        super(target, speed, dps, bearing);
        this.wayPoints = wayPoints;
        setBearing(0);
        setTarget(null);
        setPosition(wayPoints[0].x, wayPoints[0].y);
    }

    // is called each frame
    public void update(){
        moveTo();
        seeTarget();
        attackIfInRange();
    }

    // gets an array of all fireTrucks in game
    private Firetruck[] getAllFireTrucks(){
        Firetruck[] alltrucks = new Firetruck[4];
        // must access a method in game state manager or something?
        return alltrucks;
    }

    // checks for fireTrucks in sight area and sets as target if found
    private void seeTarget(){
        Vector3[] squaresInSight = createHitBox();
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


    // translation equations returns a new vector
    private Vector3 translate(Vector2 destination, Vector2 currentPos){
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
            setPosition(newPos.x, newPos.y);
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
