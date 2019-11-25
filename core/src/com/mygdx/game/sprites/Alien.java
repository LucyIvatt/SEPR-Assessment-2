package com.mygdx.game.sprites;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector3;

public class Alien extends Character {

    private Vector3[] wayPoints;
    private int currentWP; //The current index of
    private long timeWhenLastMoved = 0;
    private float timeInterval = 0.001f;

    public Alien (Vector3[] wayPoints, Unit target, int speed, int dps, int bearing){
        super(target, speed, dps, bearing);
        this.wayPoints = wayPoints;
        setBearing(0);
        setTarget(null);
    }

    // is called each frame
    public void update(){
        moveTo();
        seeTarget();
    }
    // hit boxes are arrays with length 4, where each vector3 represents the corners of the hitbox
    private Vector3[] createHitBox(){
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

    // gets an array of all fireTrucks in game
    private Firetruck[] getAllFireTrucks(){
        Firetruck[] alltrucks = new Firetruck[4];
        // must access a method in game state manager or something?
        return alltrucks;
    }

    // checks if a fireTruck is in the search area can be extended to check if walls are in front of firetrucks
    private boolean isInArea(Vector3 fireTruck, Vector3[] location){
        if (fireTruck.x <= location[1].x && fireTruck.x >= location[0].x && fireTruck.y <= location[0].y && fireTruck.y >= location[2].y){
            return true;
        } else
            return false;
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

    // Moves the alien between the wayPoints
    private void moveTo(){
        Vector3 newPos = wayPoints[currentWP];
        if (System.currentTimeMillis() > timeWhenLastMoved + timeInterval){

            timeWhenLastMoved = System.currentTimeMillis();
        }
        // need to get the time of the game to be able to make this functions
    }


}
