package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/**
 * An abstract class which contains the methods and fields required for
 * each game state of the game.
 *
 * @author Lucy Ivatt
 */
public abstract class State {

    protected GameStateManager gameStateManager;

    /**
     * Constructor to initialise the camera and mouse and set the
     * GameStateManager variable
     * @param gameStateManager the class containing the stack of States
     */
    protected State(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
    }

    /**
     * Updates the game logic before the next render() is called
     * @param deltaTime the amount of time which has passed since the last render() call
     */
    public abstract void update(float deltaTime);

    /**
     * Used to draw elements onto the screen.
     * @param spriteBatch a container for all elements which need rendering to the screen
     */
    public abstract void render(SpriteBatch spriteBatch);

    /**
     * Used to dispose of all textures, music etc. when no longer required to avoid memory leaks
     */
    public abstract void dispose();
}