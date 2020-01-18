package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Kroy;
import com.mygdx.game.misc.Button;

/**
 * An implementation of the abstract class State which controls the
 * menu screen rendering and input handling.
 *
 * @author Lucy Ivatt
 * @since 20/11/2019
 */

public class MenuState extends State {

    private final int SPACING = 100;

    private Texture background;
    private Button play;
    private Button options;
    private Button credits;
    private Button instructions;
    private Button quit;
    private Preferences saveData ;
    private Sound click = Gdx.audio.newSound(Gdx.files.internal("click.wav"));

    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
        saveData = Gdx.app.getPreferences("Kroy");
        background = new Texture("Menu.jpg");

        options = new Button(new Texture("optionspressed.png"), new Texture("options.png"),
                350,100, new Vector2((Kroy.WIDTH / 2) - 350 - (SPACING / 2), 300), false,
                false);

        play = new Button(new Texture("playpressed.png"), new Texture("play.png"),350,
                100, new Vector2((Kroy.WIDTH / 2) - 350 * 2 - SPACING - SPACING / 2, 300), false,
                false);

        credits = new Button(new Texture("creditspressed.png"), new Texture("credits.png"),
                350, 100,new Vector2((Kroy.WIDTH / 2) + SPACING + (SPACING / 2) + 350, 300) , false,
                false);

        instructions = new Button(new Texture("PressedControls.png"),
                new Texture("NotPressedControls.png"),350, 100,
                new Vector2((Kroy.WIDTH / 2) + SPACING / 2, 300), false,
                false);

        quit = new Button(new Texture("quitpressed.png"), new Texture("quit.png"),
                350, 100, new Vector2(Kroy.WIDTH / 2 - (350 / 2), 100),
                false, false);
        if (saveData.getBoolean("music", true) == true){
            Kroy.INTRO.resume(Kroy.ID);
        }

    }

    /**
     * The game logic which is executed due to specific user inputs. Is called in the update method.
     * Checks if mouse is hovering over a button and plays the animation accordingly as well as checking for
     * mouse clicks which will activate the function of the button. Also checks user settings and plays
     * effects and music accordingly.
     */
    public void handleInput() {
        if (play.mouseInRegion()){
            play.setActive(true);
            if (Gdx.input.isTouched()) {
                if (saveData.getBoolean("effects")){
                    click.play();
                }
                gameStateManager.push(new LevelSelectState(gameStateManager));
            }
        }
        else {
            play.setActive(false);
        }
        if (options.mouseInRegion()){
            options.setActive(true);
            if (Gdx.input.isTouched()) {
                if (saveData.getBoolean("effects")){
                    click.play();
                }
                gameStateManager.push(new OptionState(gameStateManager));
            }
        }
        else {
            options.setActive(false);
        }

        if (credits.mouseInRegion()){
            credits.setActive(true);
            if (Gdx.input.isTouched()) {
                if (saveData.getBoolean("effects")){
                    click.play();
                }
                gameStateManager.push(new InfoState(gameStateManager, 0));
            }
        }

        else {
            credits.setActive(false);
        }

        if (instructions.mouseInRegion()){
            instructions.setActive(true);
            if (Gdx.input.isTouched()) {
                if (saveData.getBoolean("effects")){
                    click.play();
                }
                gameStateManager.push(new InfoState(gameStateManager, 1));
            }
        }

        else {
            instructions.setActive(false);
        }

        if (quit.mouseInRegion()){
            quit.setActive(true);
            if (Gdx.input.isTouched()) {
                if (saveData.getBoolean("effects")){
                    click.play();
                }
                Gdx.app.exit();
                System.exit(0);
            }
        }
        else {
            quit.setActive(false);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
            System.exit(0);
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
     * @param spriteBatch a container for all elements which need rendering to the screen.
     */
    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0, Kroy.WIDTH, Kroy.HEIGHT);

        spriteBatch.draw(play.getTexture(), play.getPosition().x, play.getPosition().y, play.getWidth(),
                play.getHeight());

        spriteBatch.draw(options.getTexture(), options.getPosition().x, options.getPosition().y, options.getWidth(),
                options.getHeight());

        spriteBatch.draw(credits.getTexture(), credits.getPosition().x, credits.getPosition().y, credits.getWidth(),
                credits.getHeight());

        spriteBatch.draw(instructions.getTexture(), instructions.getPosition().x, instructions.getPosition().y,
                instructions.getWidth(), instructions.getHeight());

        spriteBatch.draw(quit.getTexture(), quit.getPosition().x, quit.getPosition().y, quit.getWidth(),
                quit.getHeight());

        spriteBatch.end();
    }

    /**
     * Used to dispose of all textures, music etc. when no longer required to avoid memory leaks
     */
    @Override
    public void dispose() {
        background.dispose();
        play.dispose();
        options.dispose();
        credits.dispose();
        quit.dispose();
        click.dispose();
    }
}
