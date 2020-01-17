package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Kroy;
import com.mygdx.game.misc.Button;

/**
 * Implementation of the abstract class State which contains the methods and attributes required to control the
 * Credits screen and Instructions screen.
 *
 * @author Lucy Ivatt
 */

public class InfoState extends State {

    private Texture background;
    private Button back;

    public InfoState(GameStateManager gameStateManager, int mode) { // mode 0 = Credits, mode 1 = Instructions
        super(gameStateManager);

        if (mode == 0) {
            background = new Texture("CreditsBackground.png");
        }

        else if (mode == 1) {
            background = new Texture("Instructions.png");
        }

        back = new Button(new Texture("backbutton2.png"),
                new Texture("backbutton1.png"), 100, 100,
                new Vector2(30, 960), false, false);
    }

    /**
     * The game logic which is executed due to specific user inputs. Is called in the update method.
     *
     * Checks if mouse is hovering over a button and plays the animation accordingly as well as checking for
     * mouse clicks which will activate the function of the button.
     */
    public void handleInput() {
        if (back.mouseInRegion()){
            back.setActive(true);
            if (Gdx.input.isTouched()) {
                gameStateManager.pop();
            }
        }
        else {
            back.setActive(false);
        }
    }

    /**
     * Updates the game logic before the next render() is called
     * @param deltaTime the amount of time which has passed since the last render() call
     */
    @Override
    public void update(float deltaTime) {
        handleInput();
    }

    /**
     * Used to draw elements onto the screen.
     * @param spriteBatch a container for all elements which need rendering to the screen
     */
    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0, Kroy.WIDTH, Kroy.HEIGHT);
        spriteBatch.draw(back.getTexture(), back.getPosition().x, back.getPosition().y,
                back.getWidth(), back.getHeight());
        spriteBatch.end();
    }

    /**
     * Used to dispose of all textures, music etc. when no longer required to avoid memory leaks
     */
    @Override
    public void dispose() {
        background.dispose();
        back.dispose();
    }
}

