package com.mygdx.game;

import com.badlogic.gdx.Preferences;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.MenuState;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Kroy extends ApplicationAdapter {
	public static final int WIDTH = 1920;
	public static final int HEIGHT = 1080;
	public static final String TITLE = "Kroy";

	private GameStateManager gameStateManager;
	private SpriteBatch batch;
	private Preferences saveData;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gameStateManager = new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gameStateManager.push(new MenuState(gameStateManager));
		saveData = Gdx.app.getPreferences("Kroy");

		if (saveData.getBoolean("music", true)) {
			saveData.putBoolean("music", true);
		}

		if (saveData.getBoolean("effects", true)) {
			saveData.putBoolean("effects", true);
		}
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gameStateManager.update(Gdx.graphics.getDeltaTime());
		gameStateManager.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
