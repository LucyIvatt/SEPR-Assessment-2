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

    public Texture getOnTexture() {
        return onTexture;
    }

    public void setOnTexture(Texture onTexture) {
        this.onTexture = onTexture;
    }

    public Texture getOffTexture() {
        return offTexture;
    }

    public void setOffTexture(Texture offTexture) {
        this.offTexture = offTexture;
    }

    public boolean isActive() {
        return active;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean mouseInRegion() {
        Vector2 mousePos = new Vector2(Gdx.input.getX(), Test.HEIGHT - Gdx.input.getY());
        return (mousePos.x >= (position.x) && mousePos.x <= (position.x + width) && mousePos.y >= (position.y) && mousePos.y <= (position.y + height));
    }
}
