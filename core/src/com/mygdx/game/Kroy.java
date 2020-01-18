package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.MenuState;

/**
 * Class which initialises the SpriteBatch, GameStateManager and loads sounds settings before
 * pushing the beginning MenuState instance.
 *
 * @author Lucy Ivatt
 */

public class Kroy extends ApplicationAdapter {

	public static final int WIDTH = 1920;
	public static final int HEIGHT = 1080;
	public static final String TITLE = "Kroy";
	public static Sound INTRO;
	public static long ID;

	private GameStateManager gameStateManager;
	private SpriteBatch batch;
	private Preferences saveData;

	/**
	 * Initializes the game. During the start of the game loop thread, create() will be called only once.
	 */
	@Override
	public void create () {
		INTRO = Gdx.audio.newSound(Gdx.files.internal("intro.mp3"));
		ID = INTRO.loop();
		INTRO.pause(ID);
		batch = new SpriteBatch();
		gameStateManager = new GameStateManager();
		Gdx.gl.glClearColor(0, 0, 1, 1);
		gameStateManager.push(new MenuState(gameStateManager)); // Initializes game with menu state
		saveData = Gdx.app.getPreferences("Kroy");

		if (saveData.getBoolean("music", true)) { // If no value for "music" exists in save data, set to true
			saveData.putBoolean("music", true);
		}

		if (saveData.getBoolean("effects", true)) { // If no value for "effects" exists in save data, set to true
			saveData.putBoolean("effects", true);
		}
	}

	/**
	 * Updates and draws the State on the top of the stack to the screen.
	 */
	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gameStateManager.update(Gdx.graphics.getDeltaTime());
		gameStateManager.render(batch);
	}

	/**
	 * Garbage disposal when the game is closed
	 */
	@Override
	public void dispose () {
		batch.dispose();
	}
}
