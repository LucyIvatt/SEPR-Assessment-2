package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * The GameStateManager class is used to easily control
 * which State is currently being rendered to the screen.
 *
 * @author Lucy Ivatt
 * @since 20/11/2019
 */

public class GameStateManager {

    private Stack<State> states;
    /**
     * Initializes the stack of game States
     */
    public GameStateManager() {
        states = new Stack<State>();
    }

    /**
     * Adds a State to the top of the stack
     * @param state State object to be pushed onto the stack
     */
    public void push (State state) {
        states.push(state);
    }

    /**
     * Removes a State from the top of the stack
     */
    public void pop() {
        states.peek().dispose();
        states.pop();
    }

    /**
     * Removes a State from the stack then adds a
     * State to the top of the stack.
     *
     * @param state Game State to add to the stack
     */
    public void set(State state) {
        states.peek().dispose();
        states.pop();
        states.push(state);
    }

    /**
     * Updates game logic for the state at the top of the stack
     *
     * @param deltaTime the amount of time which has passed since the last render() call.
     */
    public void update(float deltaTime) {
        states.peek().update(deltaTime);
    }

    /**
     * Used to draw all elements from the State at the top of the stack to the screen.
     * @param spriteBatch a container for all elements which need rendering to the screen
     */
    public void render(SpriteBatch spriteBatch) {
        states.peek().render(spriteBatch);
    }

}
