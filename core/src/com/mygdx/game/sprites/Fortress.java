package com.mygdx.game.sprites;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import java.util.Timer;
import java.util.TimerTask;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Test;

import java.util.Random;

public class Fortress extends Unit {

    private int spawnRate;
    private Texture texture;
    private Timer timer = new Timer();
    private ArrayList<Alien> aliens;

    private ArrayList<Vector2> objectPositions;

     public Fortress(Vector2 position,int width, int height, Texture texture) { // Default constructor
        super(position, width, height, texture);
        this.spawnRate = 5;
    }

    public Fortress(Vector2 position, int width, int height, Texture texture, String img) { // basic fortress
        super(position, width, height, texture);
        this.spawnRate = 5;
    }

    public Fortress(Vector2 position, int width, int height, Texture texture, int spawnRate) {
        super(position, width, height, texture);
        this.spawnRate = spawnRate;
    }

    public int getSpawnRate() {
        return this.spawnRate;
    }

    public void setSpawnRate(int new_spawnRate) {
        this.spawnRate = new_spawnRate;
    }

//    public void produceAlien(int spawnRate) {
//        while (aliens.size() < 20) {
//            timer.schedule(new TimerTask(){
//                @Override
//                public void run() {
//
//                    while (true) {
//                        Random rand = new Random();
//                        int x1 = rand.nextInt(Test.WIDTH);
//                        int y1 = rand.nextInt(Test.HEIGHT);
//                        int x2 = rand.nextInt(Test.WIDTH);
//                        int y2 = rand.nextInt(Test.HEIGHT);
//
//                        Vector2 waypoint1 = new Vector2(x1,y1);
//                        Vector2 waypoint2 = new Vector2(x2,y2);
//                        if (checkWaypoint(waypoint1) && checkWaypoint(waypoint2)) {
//                            break;
//                        }
//                    }
//
//                    //Vector2[] waypoints = {waypoint1, waypoint2};
//                    //Alien alien = new Alien(, 10, 10, 100, 10, );
//                    //aliens.add(alien);
//                }
//            },1000*this.spawnRate,1000*this.spawnRate); // 1000*5=5000 mlsec. i.e. 5 seconds. u can change accordingly
//            // given two times, first is for first time to execute this
//            // code and second is for interval time
//        }
//    }

    public boolean checkWaypoint(Vector2 waypoint1) {
         if (objectPositions.contains(waypoint1)) {
             return false;
         }

         return true;
    }


}

// check alien way points dont over lap
//