package com.mygdx.game;

import com.badlogic.gdx.Preferences;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.MenuState;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Test extends ApplicationAdapter {
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 700;
	public static final String TITLE = "Test Game";

	private GameStateManager gsm;
	private SpriteBatch batch;
	private Preferences settings;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuState(gsm));
		settings = Gdx.app.getPreferences("My Preferences");
		settings.putBoolean("music", true);
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
