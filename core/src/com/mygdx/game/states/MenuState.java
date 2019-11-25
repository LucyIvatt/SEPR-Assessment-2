package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.mygdx.game.Button;
import com.mygdx.game.Test;
import com.badlogic.gdx.audio.Sound;

public class MenuState extends State {

    private Texture background;
    private Button play;
    private Vector2 position;
    private Music BgMusic;


    public MenuState(GameStateManager gsm) {
        super(gsm);
        BgMusic = Gdx.audio.newMusic(Gdx.files.internal("core/assets/totallynottheskyrimtheme.mp3"));
        background = new Texture("menuExample.png");
        play = new Button(new Texture("blue.jpg"), new Texture("green.jpg"), 100, 20, new Vector2(100, 200));
        BgMusic.play();
    }

    @Override
    public void handleInput() {
        // Got hover button working but the coordinate system for position go from top left and the coordinate system for drawing goes from bottom right BRUH
        if (play.mouseInRegion()){
            play.setActive(true);
            if (Gdx.input.isTouched()) {
                BgMusic.stop();
                gsm.set(new PlayState(gsm));
            }
            }
        else {
            play.setActive(false);
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
        sb.draw(play.getTexture(), 100, 200, 100, 20);
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        BgMusic.dispose();
    }
}
