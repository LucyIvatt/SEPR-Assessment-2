package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Button;
import com.mygdx.game.Test;

public class MenuState extends State implements InputProcessor {

    private Texture background;
    private Button play;
    private Button musicToggle;
    private Preferences settings;


    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("menuExample.png");
        play = new Button(new Texture("blue.jpg"), new Texture("green.jpg"), 400, 60, new Vector2(300, 200));
        musicToggle = new Button(new Texture("blue.jpg"), new Texture("green.jpg"), 400, 60, new Vector2(300, 400));
        Gdx.input.setInputProcessor(this);
        settings = Gdx.app.getPreferences("My Preferences");
    }
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (musicToggle.clickInRegion(screenX, screenY)) {
            if (settings.getBoolean("music")) {
                settings.putBoolean("music", false);
            } else {
                settings.putBoolean("music", true);
            }

            System.out.println(settings.getBoolean("music"));
            System.out.println("END");
            return false;
        }

        return false;
    }
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }


    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public void handleInput() {
        // Got hover button working but the coordinate system for position go from top left and the coordinate system for drawing goes from bottom right BRUH
        if (play.mouseInRegion()) {
            play.setActive(true);
            if (Gdx.input.isTouched()) {
                gsm.set(new PlayState(gsm));
            }
        } else {
            play.setActive(false);
        }

        if (musicToggle.mouseInRegion()) {
            musicToggle.setActive(true);
        } else {
            musicToggle.setActive(false);
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, Test.WIDTH, Test.HEIGHT);
        sb.draw(play.getTexture(), play.getPosition().x, play.getPosition().y, play.getWidth(), play.getHeight());
        sb.draw(musicToggle.getTexture(), musicToggle.getPosition().x, musicToggle.getPosition().y, musicToggle.getWidth(), musicToggle.getHeight());
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
