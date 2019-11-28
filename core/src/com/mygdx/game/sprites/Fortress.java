package com.mygdx.game.sprites;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import java.util.Timer;
import java.util.TimerTask;

public class Fortress extends Unit {

    private int spawnRate;
    private String fortressImg;
    private Texture texture;
    private Timer timer = new Timer();

    public Fortress(int posX, int posY, int maxHealth, int currentHealth, int range, int spawnRate, String img) {
        // (posX, posY, width, height, active, maxHealth, currentHealth, range)
        super();
        this.spawnRate = spawnRate;
        this.fortressImg = img;
    }

    public int getSpawnRate() {
        return this.spawnRate;
    }

    public void setSpawnRate(int new_spawnRate) {
        this.spawnRate = new_spawnRate;
    }

    public void produceAlien(int spawnRate) {
        Firetruck ft = new Firetruck(50, True, new Unit(), 10, 5, 0); // List of firetrucks from current gamestate?
        ArrayList<String> aliens = new ArrayList<String>(); // list of aliens in current game state?

        while (aliens.length() < 20) {
            timer.schedule(new TimerTask(){
                @Override
                public void run() {
                    aliens.add("alien");
                }
            },1000*this.spawnRate,1000*this.spawnRate); // 1000*5=5000 mlsec. i.e. 5 seconds. u can change accordngly
            // given two times, first is for first time to execute this
            // code and second is for interval time
        }
    }
}
