package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Kroy;
import com.mygdx.game.misc.Button;

/**
 * An implementation of the abstract class which controls the option menu. Uses InputProcessor due to
 * toggle buttons which need the input event handling rather than polling for input.
 *
 * @author Lucy Ivatt
 */

public class OptionState extends State implements InputProcessor {

    private Texture background;;

    private Button back;
    private Button musicToggle;
    private Button effectsToggle;

    private Preferences saveData;
    private Texture tick;
    private Texture cross;
    private Sound click = Gdx.audio.newSound(Gdx.files.internal("click.wav"));

    public OptionState(GameStateManager gameStateManager) {
        super(gameStateManager);

        saveData = Gdx.app.getPreferences("Kroy");
        background = new Texture("optionsMenu.png");

        back = new Button(new Texture("backbutton2.png"), new Texture("backbutton1.png"),
                100, 100, new Vector2(30, 960), false, false);

        tick = new Texture("tick.png");
        cross = new Texture("cross.png");

        musicToggle = new Button(tick, cross, 100, 100, new Vector2(1091, 389),
                saveData.getBoolean("music"), false);

        effectsToggle = new Button(tick, cross, 100, 100, new Vector2(1274, 174),
                saveData.getBoolean("effects"), false);

        Gdx.input.setInputProcessor(this);
    }

    /**
     * Updates the game logic before the next render() is called. Unused due to input handling required and not input
     * polling.
     * @param deltaTime the amount of time which has passed since the last render() call
     */
    public void update(float deltaTime) {

    }

    /**
     * Used to draw elements onto the screen.
     * @param spriteBatch a container for all elements which need rendering to the screen.
     */
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0, Kroy.WIDTH, Kroy.HEIGHT);
        spriteBatch.draw(back.getTexture(), back.getPosition().x, back.getPosition().y, back.getWidth(), back.getHeight());
        spriteBatch.draw(musicToggle.getTexture(), musicToggle.getPosition().x, musicToggle.getPosition().y,
                musicToggle.getWidth(), musicToggle.getHeight());
        spriteBatch.draw(effectsToggle.getTexture(), effectsToggle.getPosition().x, effectsToggle.getPosition().y,
                effectsToggle.getWidth(), effectsToggle.getHeight());
        spriteBatch.end();
    }

    /**
     * Used to dispose of all textures, music etc. when no longer required to avoid memory leaks
     */
    public void dispose() {
        background.dispose();
        back.dispose();
        musicToggle.dispose();
        effectsToggle.dispose();
        tick.dispose();
        cross.dispose();
    }

    /**
     * Used to handle the event when a user clicks. Checks if the click was within any button on the screen
     * and then activates the buttons functionality if so.
     * @param screenX x coordinate of click event
     * @param screenY y coordinate of click event
     * @param pointer pointer used in event
     * @param button mouse button clicked during the event
     */
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (musicToggle.clickInRegion(screenX, screenY)) {
            if (saveData.getBoolean("music") == true) {
                saveData.putBoolean("music", false);
                if (saveData.getBoolean("effects")) {
                    click.play();
                }
                Kroy.INTRO.pause(Kroy.ID);
                musicToggle.setActive(false);

            }
            else {
                saveData.putBoolean("music", true);
                if (saveData.getBoolean("effects")) {
                    click.play();
                }
                musicToggle.setActive(true);
                Kroy.INTRO.resume(Kroy.ID);
            }
            return false;
        }

        else if (effectsToggle.clickInRegion(screenX, screenY)) {
            if (saveData.getBoolean("effects") == true) {
                saveData.putBoolean("effects", false);
                effectsToggle.setActive(false);
            }

            else {
                saveData.putBoolean("effects", true);
                click.play();
                effectsToggle.setActive(true);
            }
            return false;
        }

        else if (back.clickInRegion(screenX, screenY)) {
            if (saveData.getBoolean("effects")) {
                if (saveData.getBoolean("effects")) {
                    click.play();
                }
            }
            gameStateManager.pop();
        }
        saveData.flush();
        return false;
    }

    /**
     * Used to handle the event when a moves their mouse on the game screen. Checks if the mouse hovers over the back
     * button and if so triggers animation.
     * @param screenX x coordinate of mouse on event
     * @param screenY y coordinate of mouse on event
     */
    public boolean mouseMoved(int screenX, int screenY) {
        if (back.clickInRegion(screenX, screenY)) {
            back.setActive(true);
        } else {
            back.setActive(false);
        }
        return false;
    }

    // -------------------------------------------------------------------------------
    // Unused methods implemented from the Input Processor class for input handling
    // -------------------------------------------------------------------------------

    public boolean scrolled(int amount) {
        return false;
    }

    public boolean keyDown(int keycode) {
        return false;
    }

    public boolean keyUp(int keycode) {
        return false;
    }

    public boolean keyTyped(char character) {
        return false;
    }

    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }
}