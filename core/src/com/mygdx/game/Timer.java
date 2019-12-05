package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Timer {
    private BitmapFont font;
    private float deltaTime = 0;
    private String timeString;

    public Timer() {
        font = new BitmapFont();
    }

    public void drawTime(SpriteBatch batch) {
        deltaTime += (Gdx.graphics.getDeltaTime());
        timeString = String.format("%.0f", deltaTime);
        font.draw(batch, "Time: " + timeString, 50, 30);

    }

}
