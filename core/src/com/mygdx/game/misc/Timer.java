package com.mygdx.game.misc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Kroy;

public class Timer {
    private float deltaTime = 0;
    private boolean stopped;
    private String timeString = "0";

    public Timer() {
        stopped = false;
    }

    public void update(){
        if (!this.stopped) {
            deltaTime += (Gdx.graphics.getDeltaTime());
            timeString = String.format("%.0f", deltaTime);
        }
    }

    public void stop(){
        this.stopped = true;
    }


    public void start(){
        this.stopped = false;
    }

    public void drawTime(SpriteBatch batch, BitmapFont font) {
        font.draw(batch, "Time Elapsed: " + timeString, 810, Kroy.HEIGHT - 1005);
    }

    public float getDeltaTime() {
        return deltaTime;
    }
}
