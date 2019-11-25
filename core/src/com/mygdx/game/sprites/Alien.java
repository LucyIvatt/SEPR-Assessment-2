package com.mygdx.game.sprites;
import com.badlogic.gdx.math.Vector3;

public class Alien extends Character {

    private Vector3[] wayPoints;
    private int currentWP; //The current index of waypoints


    public Alien (Vector3[] wayPoints, int speed, int dps,){
        this.wayPoints = wayPoints;
        setBearing(0);
        setTarget(null);
    }
    public void update(){
        moveTo();

    }

    private void seeTarget(){

    }

    private void nextWayPoint(){
        // checks whether the alien is already at the next waypoint if so sets the target waypoint to the next in line
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
        Vector3 newPos = getPosition();
    }


}
