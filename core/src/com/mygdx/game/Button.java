package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Button {

    private Texture onTexture;
    private Texture offTexture;
    private boolean active;
    private int width;
    private int height;
    private Vector2 position;

    public Button(Texture on, Texture off, int width, int height, Vector2 position) {
        this.onTexture = on;
        this.offTexture = off;
        this.width = width;
        this.height = height;
        this.position = position;
        this.active = false;
    }

    public Texture getTexture() {
        if(active == false) {
            return offTexture;
        }
        else {
            return onTexture;
        }
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean mouseInRegion() {
        Vector2 mousePos = new Vector2(Gdx.input.getX(), 700 - Gdx.input.getY());
        return (mousePos.x >= (position.x) && mousePos.x <= (position.x + width) && mousePos.y >= (position.y) && mousePos.y <= (position.y + height));
    }
}
