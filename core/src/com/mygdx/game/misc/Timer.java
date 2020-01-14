package com.mygdx.game.misc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Kroy;

public class Timer {
    private float deltaTime;
    private String timeString;

    public Timer() {
        deltaTime = 0;
        timeString = "0";
    }

    public void update(){
        deltaTime += (Gdx.graphics.getDeltaTime());
        timeString = String.format("%.0f", deltaTime);
    }

    public void drawTime(SpriteBatch batch, BitmapFont font) {
        font.draw(batch, "Time Elapsed: " + timeString, 810, Kroy.HEIGHT - 1005);
    }

    public float getDeltaTime() {
        return deltaTime;
    }
}
