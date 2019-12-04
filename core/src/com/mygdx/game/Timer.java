package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Timer {
    private BitmapFont font;
    private float deltaTime = 0;
    String time;

    public Timer() {
        font = new BitmapFont();
    }

    public void drawTime(SpriteBatch batch) {
        deltaTime += (Gdx.graphics.getDeltaTime());
        time = String.format("%.0f", deltaTime);
        font.draw(batch, time, 100, 100);

    }
}
