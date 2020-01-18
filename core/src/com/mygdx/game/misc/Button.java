package com.mygdx.game.misc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Kroy;

/**
 * The Button class is used to create button objects for use on the MenuScreen
 * which can be used to change the current game State
 *
 * @author Lucy Ivatt
 * @since 21/11/2019
 */

public class Button {

    private Texture onTexture;
    private Texture offTexture;
    private int width;
    private int height;
    private Vector2 position;
    private boolean locked;
    private boolean active;

    public Button(Texture onTexture, Texture offTexture, int width, int height, Vector2 position, boolean active, boolean locked) {
        this.onTexture = onTexture;
        this.offTexture = offTexture;
        this.width = width;
        this.height = height;
        this.position = position;
        this.active = active;
        this.locked = locked;
    }

    /**
     * A method which determines if the mouse is hovering over the button.
     * @return true if mouse over button or false if not
     */
    public boolean mouseInRegion() {
        Vector2 mousePos = new Vector2(Gdx.input.getX(), Kroy.HEIGHT - Gdx.input.getY());
        return (mousePos.x >= (position.x) && mousePos.x <= (position.x + width) && mousePos.y >= (position.y) && mousePos.y <= (position.y + height));
    }

    /**
     * A method which determines a click, given by integer coordinates, is within the buttons region
     * @param x x coordinate of mouse click
     * @param y y coordinate of mouse click
     * @return true if the click is on the button or false if not
     */
    public boolean clickInRegion(int x, int y) {
        return (x >= position.x && x <= (position.x + width) && Kroy.HEIGHT - y >= position.y && Kroy.HEIGHT - y <= (position.y + height));

    }

    /**
     * Used to dispose of all textures, music etc. when no longer required to avoid memory leaks
     */
    public void dispose() {
        onTexture.dispose();
        offTexture.dispose();
    }

    /**
     * @return Texture of the button which needs to be rendered. This changes depending on whether or not the button is active.
     */
    public Texture getTexture() {
        if(active == false) {
            return offTexture;
        }
        else {
            return onTexture;
        }
    }

    public void setOnTexture(Texture onTexture) {
        this.onTexture = onTexture;
    }


    public void setOffTexture(Texture offTexture) {
        this.offTexture = offTexture;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Vector2 getPosition() {
        return position;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
