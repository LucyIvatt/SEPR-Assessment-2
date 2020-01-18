package com.mygdx.game.misc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Kroy;

/**
 * The Timer class is used to keep track of how long the level has been running and is used to check if the
 * levels time limit has been reached.
 *
 * @author Lucy Ivatt
 */


public class Timer {
    private float time;
    private float timeLimit;
    private String timeString;


    public Timer(float timeLimit) {
        time = 0;
        this.timeLimit = timeLimit;
        timeString = "0";
    }

    /**
     * A method which updates the time every game tick using Libgdx's in-built method Gdx.graphics.deltaTime()
     */
    public void update(){
        time += (Gdx.graphics.getDeltaTime());
        timeString = String.format("%.0f", timeLimit - time);
    }

    /**
     * A method which draws the current time onto the game screen.
     * @param spriteBatch the SpriteBatch used within the PlayState to render elements to the screen
     * @param font the BitmapFont which will be used for the text
     */
    public void drawTime(SpriteBatch spriteBatch, BitmapFont font) {
        font.draw(spriteBatch, "Time Remaining: " + timeString, 780, Kroy.HEIGHT - 1005);
    }

    public float getTime() {
        return time;
    }
}
